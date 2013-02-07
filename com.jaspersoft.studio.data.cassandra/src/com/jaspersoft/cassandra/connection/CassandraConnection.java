package com.jaspersoft.cassandra.connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.jasperreports.engine.JRException;

import org.apache.log4j.Logger;

import com.jaspersoft.cassandra.CassandraDataSource;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class CassandraConnection implements Connection {
    private Connection connection;

    private String jdbcURL;

    private final static Logger logger = Logger.getLogger(CassandraConnection.class);

    private final Pattern urlPattern = Pattern.compile("jdbc:cassandra://(.+):([0-9]+)/?.*", Pattern.CASE_INSENSITIVE);

    public CassandraConnection(String jdbcURL) throws ClassNotFoundException, JRException {
        this.jdbcURL = jdbcURL;
        Class.forName(CassandraDataSource.DRIVER_NAME);
        validateURL();
        createConnection();
    }

    private void validateURL() throws JRException {
        Matcher matcher = urlPattern.matcher(jdbcURL);
        if (matcher.matches()) {
            // Group numbers are hardcoded since the pattern is known
            String hostName = matcher.group(1);
            String port = matcher.group(2);
            validateHostAndPort(hostName, Integer.parseInt(port));
        } else {
            throw new JRException("Invalid Cassandra JDBC url: " + jdbcURL);
        }
    }

    private void validateHostAndPort(String hostName, Integer port) throws JRException {
        InetSocketAddress inetSocket = null;
        Socket socket = null;
        try {
            socket = new Socket();
            inetSocket = new InetSocketAddress(hostName, port);
            if (inetSocket.isUnresolved()) {
                throw new JRException("Failed connecting to: " + inetSocket);
            }
            socket.connect(inetSocket, 10000);
        } catch (Exception ex) {
            throw new JRException(ex);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection = null;
        }
    }

    @Override
    public boolean isClosed() throws SQLException {
        if (connection != null) {
            return false;
        }
        return true;
    }

    private void createConnection() {
        if (connection == null) {
            if (jdbcURL == null) {
                logger.error("Cassandra JDBC url not defined");
                return;
            }
            try {
                connection = DriverManager.getConnection(jdbcURL, "", "");
            } catch (SQLException e) {
                e.printStackTrace();
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    connection = null;
                }
            }
            logger.info("Cassandra connection created");
        } else {
            logger.info("Cassandra connection reused");
        }
    }

    public String test() {
        if (connection != null) {
            return "Connected successfully.";
        }
        return "No connection";
    }

    public Connection getSqlConnection() {
        return connection;
    }

    public String getJdbcURL() {
        return jdbcURL;
    }

    /**
     * Extracted from iReport code
     * 
     * Thanx to Jackie Manning j.m@programmer.net for this method!!
     */
    public static String getJdbcTypeClass(ResultSetMetaData resultSetMetaData, int index) {
        String className = "java.lang.Object";

        try {
            className = resultSetMetaData.getColumnClassName(index);
            className = getJRFieldType(className);

        } catch (Exception ex) {
            try {
                int type = resultSetMetaData.getColumnType(index);
                switch (type) {
                case java.sql.Types.TINYINT:
                case java.sql.Types.BIT:
                    className = "java.lang.Byte";
                    break;
                case java.sql.Types.SMALLINT:
                    className = "java.lang.Short";
                    break;
                case java.sql.Types.INTEGER:
                    className = "java.lang.Integer";
                    break;
                case java.sql.Types.FLOAT:
                case java.sql.Types.REAL:
                case java.sql.Types.DOUBLE:
                case java.sql.Types.NUMERIC:
                case java.sql.Types.DECIMAL:
                    className = "java.lang.Double";
                    break;
                case java.sql.Types.CHAR:
                case java.sql.Types.VARCHAR:
                    className = "java.lang.String";
                    break;

                case java.sql.Types.BIGINT:
                    className = "java.lang.Long";
                    break;
                case java.sql.Types.DATE:
                    className = "java.util.Date";
                    break;
                case java.sql.Types.TIME:
                    className = "java.sql.Time";
                    break;
                case java.sql.Types.TIMESTAMP:
                    className = "java.sql.Timestamp";
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return className;
    }

    /**
     * Extracted from iReport code
     * 
     * Return the correct field type...
     * 
     */
    public static String getJRFieldType(String type) {
        if (type == null)
            return "java.lang.Object";
        if (type.equals("java.lang.Boolean") || type.equals("boolean"))
            return "java.lang.Boolean";
        if (type.equals("java.lang.Byte") || type.equals("byte"))
            return "java.lang.Byte";
        if (type.equals("java.lang.Integer") || type.equals("int"))
            return "java.lang.Integer";
        if (type.equals("java.lang.Long") || type.equals("long"))
            return "java.lang.Long";
        if (type.equals("java.lang.Double") || type.equals("double"))
            return "java.lang.Double";
        if (type.equals("java.lang.Float") || type.equals("float"))
            return "java.lang.Float";
        if (type.equals("java.lang.Short") || type.equals("short"))
            return "java.lang.Short";
        if (type.startsWith("["))
            return "java.lang.Object";
        return type;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public Statement createStatement() throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return null;
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return null;
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        return null;
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return false;
    }

    @Override
    public void commit() throws SQLException {
    }

    @Override
    public void rollback() throws SQLException {
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return null;
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return false;
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
    }

    @Override
    public String getCatalog() throws SQLException {
        return null;
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return 0;
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return null;
    }

    @Override
    public void clearWarnings() throws SQLException {
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
            throws SQLException {
        return null;
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return null;
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return null;
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
    }

    @Override
    public void setHoldability(int holdability) throws SQLException {
    }

    @Override
    public int getHoldability() throws SQLException {
        return 0;
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return null;
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return null;
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
            int resultSetHoldability) throws SQLException {
        return null;
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
            int resultSetHoldability) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return null;
    }

    @Override
    public Clob createClob() throws SQLException {
        return null;
    }

    @Override
    public Blob createBlob() throws SQLException {
        return null;
    }

    @Override
    public NClob createNClob() throws SQLException {
        return null;
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return null;
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        return false;
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return null;
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return null;
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return null;
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return null;
    }
}