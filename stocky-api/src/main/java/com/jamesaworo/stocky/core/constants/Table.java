package com.jamesaworo.stocky.core.constants;

/**
 * @author Aworo James
 * @since 4/16/23
 */
public class Table {
	public static final String PREFIX = "STOCKY_";

	// AUTHENTICATION TABLES
	public static final String AUTH = PREFIX + "AUTH";
	public static final String AUTH_ROLE = AUTH + "_ROLE";
	public static final String AUTH_PERMISSION = AUTH + "_PERMISSION";
	public static final String ROLE_PERMISSION_TABLE = AUTH + "_ROLE_PERMISSION";


	// SETTING TABLES
	public static final String SETTING = PREFIX + "SETTING";
	public static final String SETTING_DASHBOARD = SETTING + "_DASHBOARD";
	public static final String SETTING_BACKUP_RESTORE = SETTING + "_BACKUP";
	public static final String SETTING_EXPENSES = SETTING + "_EXPENSE";
	public static final String SETTING_NOTIFICATION = SETTING + "_NOTIFICATION";
	public static final String SETTING_PAYMENT_METHOD = SETTING + "_PAYMENT_METHOD";
	public static final String SETTING_TAX = SETTING + "_TAX";
	public static final String SETTING_STOCK = SETTING + "_STOCK";
	public static final String SETTING_PEOPLE = SETTING + "_PEOPLE";
	public static final String SETTING_PRODUCT = SETTING + "_PRODUCT";
	public static final String SETTING_SALE = SETTING + "_SALE";
	public static final String SETTING_OPTIONS = SETTING + "_OPTION";

	// PRODUCT TABLES
	public static final String PRODUCT = PREFIX + "PRODUCTS";
	public static final String PRODUCT_CATEGORY = PRODUCT + "_CATEGORIES";
	public static final String PRODUCT_VARIATIONS = PRODUCT + "_VARIATIONS";
	public static final String PRODUCT_UNIT_OF_MEASURE = PRODUCT + "_UNIT_OF_MEASURES";
	public static final String PRODUCT_STATUS = PRODUCT + "_STATUS";
	public static final String PRODUCT_TAX = PRODUCT + "_TAXES";
	public static final String PRODUCT_BASIC = PREFIX + "PRODUCT_BASICS";
	public static final String PRODUCT_PRICE = PREFIX + "PRODUCT_PRICES";

	// COMPANY TABLES
	public static final String COMPANY = PREFIX + "COMPANY";
	public static final String COMPANY_BUSINESS_CATEGORY_TABLE = COMPANY + "_BUSINESS_CATEGORIES";
	public static final String COMPANY_EXPENSES = COMPANY + "_EXPENSES";
	public static final String COMPANY_EXPENSES_CATEGORY = COMPANY + "_EXPENSES_CATEGORIES";

	public static final String COMPANY_BASIC_DETAILS = COMPANY + "_BASIC_DETAILS";
	public static final String COMPANY_ADMIN_DETAILS = COMPANY + "_ADMIN_DETAILS";
	public static final String COMPANY_REGION_DETAILS = COMPANY + "_REGION_DETAILS";
	public static final String COMPANY_PAYMENT_OPTION = COMPANY + "_PAYMENT_OPTIONS";
	public static final String COMPANY_LOCATION = COMPANY + "_LOCATIONS";

}