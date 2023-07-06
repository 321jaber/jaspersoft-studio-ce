/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>OBC Args</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.OBCArgs#getEntries <em>Entries</em>}</li>
 * </ul>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getOBCArgs()
 * @model
 * @generated
 */
public interface OBCArgs extends OrderByClauseArgs
{
  /**
   * Returns the value of the '<em><b>Entries</b></em>' containment reference list.
   * The list contents are of type {@link com.jaspersoft.studio.data.sql.OrderByClauseArg}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entries</em>' containment reference list.
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getOBCArgs_Entries()
   * @model containment="true"
   * @generated
   */
  EList<OrderByClauseArg> getEntries();

} // OBCArgs
