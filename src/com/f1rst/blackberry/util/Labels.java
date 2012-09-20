package com.f1rst.blackberry.util;

/**
 *
 * @author ivaylo
 *
 * ERR - error messages(in case of serious errors)
 * INF - info messages(all information messages)
 * EXC - exceptions(exceptions messages)
 * WRN - warning messages()
 * LBL - labels(short labels and status messages)
 */
public class Labels {
	
    public final static String LBL_APP_NAME = "F1rst"; //SHOULD NOT BE TRANSLATED
    public final static String LBL_APP_NAME_TITLE = "F1rst"; 
    
    public static String LBL_LOGGING = ""; //miss
    public static String LBL_SIGNING_IN = ""; //miss
    public static String LBL_CANCELLED = ""; //miis
    public static String LBL_RETRY = ""; //miss
    public static String LBL_REFRESH = ""; //miss
    public static String LBL_REFRESHING = ""; //miss
    public static String LBL_RETRYING = ""; //miss
    public static String LBL_UPDATING = ""; //miss
    public static String LBL_OPERATION_FAILED = "";  //miss
    public static String LBL_BACK = ""; //miss
    public static String LBL_SAVE = ""; //miss
           
	public static String LBL_SELECT = ""; //<string name="">Select Action</string> ?? <string name="">Select the search type"</string> 
    public static String LBL_CONTINUE = ""; //miss
    public static String LBL_CONFIRM = ""; //miss
    public static String LBL_SEARCHING = "";//"Searching..."; //miss,modified without ...
    public static String LBL_SEARCH = "";
    public static String INF_GETTING_DETAILS = "Getting details..."; //miss

    public static String INF_CONNECTION_TIMED_OUT = "";//"connection timed out"; <string name="">Internet connection is not available</string> 
    															// ?? <string name="">Service is unavailable. Try again later</string>
    public static String INF_UNABLE_TO_COMPLETE = ""; //miss or same as INF_CONNECTION_TIMED_OUT
    public static String INF_UPDATED = ""; //miss
    
    public static String INF_INVALID_DATA = ""; //<string name="">Incorrect username or password</string>
    public static String LBL_SETTINGS = ""; //miss
    public static String LBL_HELP = ""; //miss
    public static String LBL_HELP_ABOUT = ""; //miss
    
    public static String LBL_FEEDBACK = ""; //miss
    public static String LBL_BUGS_FEEDBACK = "";//miss   
    public static String LBL_LOG_OUT = ""; 
    public static String INF_ARE_YOU_SURE_LOG_OUT = "";//miss

    public static String LBL_NAME = ""; 
    public static String LBL_NEXT = "";   //Show the next 20 ? 
    public static String LBL_PASSWORD = "";
    public static String LBL_SCHOOL = "";//miss
    public static String LBL_DESCRIPTION = "";
    public static String LBL_CLICK_TO_CHANGE = "";//miss
    public static String INF_ARE_YOU_SURE_YOU_WANT_TO_REMOVE = ""; //miss
    
    public static String LBL_ADD = ""; //<string name="">Add to Cart</string> 
    public static String LBL_REMOVE = ""; //<string name="">Delete</string> 
    public static String LBL_EDIT = ""; //miss
    public static String LBL_EMAIL = ""; //<string name="">e-mail</string> 
    public static String LBL_EMAIL_ADDRESS = ""; // ==//== 
    public static String LBL_OK = "";
    public static String LBL_OTHER = "";//miss
    public static String LBL_OTHERS = "";//miss
    public static String LBL_TEXT = "";//miss
    public static String LBL_CANCEL = "";
    public static String LBL_DETAILS = "";
    public static String LBL_DELETE = "";
    public static String LBL_SECTORS = "";//miss
    public static String INF_NO_SECTORS = ""; //miss
    public static String INF_NO_FAMILIES = ""; //miss

	public static String INF_EXIT = ""; //Do you really want to exit?
	
	public static String LBL_CATEGORIES = "";
	public static String INF_NO_CATEGORIES = "";
	public static String INF_NO_PRODUCTS = "";	
	public static String INF_INVALID_CREDENTIALS = "";
	
	public static String INF_NO_SEARCH_RESULTS = "";
	public static String INF_NO_RESULTS = "";
	public static String INF_NO_PRODUCT_DETAILS = ""; //<string name="">No product details are available</string>
	
	public static String LBL_SEARCH_RESULTS = "";
	public static String LBL_PRODUCT_DETAILS = "";
	public static String LBL_FAVOURITES = "";

	public static String LBL_CART = "";

	public static String LBL_IMAGE_GALLERY = "";
	public static String LBL_TECHNICAL_DETAILS = "";
	public static String LBL_BRAND = "";
	public static String LBL_BRANDS = "";
	
	public static String LBL_AVAILABILITY = "";
	public static String LBL_RELATED_PRODUCTS = "";	

	public static String LBL_FEES = "";
	public static String LBL_TOTAL_PRICE_NO_VAT = "";
	public static String LBL_TOTAL_PRICE_VAT = "";
	public static String LBL_BUY = "";
	public static String LBL_HISTORY = "";
	public static String LBL_ADD_TO_FAVORITES = "";
	public static String LBL_MORE_DETAILS = "";
	public static String LBL_ADDITIONAL_DETAILS = "";
	
	public static String EURO_SIGN = "\u20AC";
	public static String STAR_SIGN = "\u22C6";//		STAR OPERATOR //http://www.fileformat.info/info/unicode/char/22c6/index.htm
	public static String STAR_SIGN_2 = "\u2605";//	black star
	public static String STAR_SIGN_3 = "\u066D";//	arabic five pointed star 
	public static String STAR_SIGN_4 = "\u2B50";//	white medium star U+2B50
	public static String STAR_SIGN_5 = "\u2606";//	http://en.wikipedia.org/wiki/Miscellaneous_Symbols
	

	public static String EURO_SIGN_BRACKETS = "(\u20AC)";
    
	public static String LBL_EAN = "";
	public static String LBL_CODE = "";
	public static String LBL_MANUF_CODE = "";
	public static String LBL_MANUF_WARRANTY = "";
	//public static String LBL_PRICE = "" ;//+ EURO_SIGN_BRACKETS;
	public static String LBL_LIST_PRICE = "" ;//+ EURO_SIGN_BRACKETS;
	//public static String LBL_END_PRICE = "" + EURO_SIGN_BRACKETS;
	public static String LBL_END_USER_WARRANTY = "";
	
	//public static String INF_ADDED_TO_FAVORITES = "";
	//public static String INF_ALREADY_IN_FAVORITES = "";
	
	
	public static String INF_NO_FAVORITE_PRODUCTS = "";
	public static String INF_NO_HISTORY_PRODUCTS = "";
	
	public static String LBL_FAMILIES = "";
	public static String LBL_ADDRESSES = "";	
	public static String INF_NO_ADDRESSES = "";
	//public static String LBL_CARIERS = "";
	public static String INF_NO_CARIERS = "";
	
	public static String LBL_CARRIER = "";
	public static String LBL_ADDRESS = "";
	public static String LBL_ORDER_SUMMARY = "";
	public static String INF_ORDER_SUCCESS = "";
	public static String INF_ORDER_PROBLEM = "";
	public static String LBL_GALLERY = "";
	
	public static String LBL_CODE_SMALL = "";
	public static String LBL_AVAILABILITY_SMALL = "";
	
	public static String INF_ENTER_VALID_QUANTITY = "";
	public static String INF_NO_AVAILABILITY = "";
	public static String INF_NO_ENOUGH_QUANTITY = "";
	
	public static String LBL_QUANTITY = "";
	
	public static String LBL_ADD_TO_CART = "";
	
	public static String LBL_EVENTS = "";
	public static String LBL_PROFILE = "";
	public static String LBL_CUSTOMER_CODE = "";
	public static String LBL_MAIL = "e-mail";
	public static String LBL_USER_CODE = "";
	public static String LBL_LANGUAGE = "";
	public static String LBL_PRICE_TAB = "";
	
	public static String LBL_START_DATE = "";
	public static String LBL_END_DATE = "";
	
	public static String LBL_ENGLISH = "";
	public static String LBL_SPANISH = "";
	public static String LBL_ITALIAN = "";
	
	public static String LBL_YOUR_PRICE = "";
	
	public static String LBL_PROMOTIONS = "";
	public static String INF_NO_PROMOTIONS = "";

		
	public static String LBL_IN_THE_FUTURE = "";
	public static String LBL_DAYSAGO = "";
	public static String LBL_YESTERDAY = "";
	public static String LBL_TODAY = "";
	public static String LBL_1YEARAGO = "";
	public static String LBL_YEARSAGO = "";
	public static String LBL_1MONTHAGO = "";
	public static String LBL_MONTHSAGO = "";
	public static String LBL_1WEEKAGO = "";
	public static String LBL_2WEEKSAGO = "";
	public static String LBL_3WEEKSAGO = "";
	
	public static String LBL_GOTOPRODUCTS = "";
	
	public static String LBL_ALL_BRANDS = "";
	public static String LBL_BRANDS_BY_LETTER = "";
	public static String LBL_SELECT_SEARCH_TYPE = "";
	
	public static String INF_UNABLE_TO_CONNECT = "";
	public static String INF_REMOVE_VERIFICATION = "";
	public static String LBL_GET_DETAILS = "";
	public static String LBL_GALLERY_TITLE = "";
	public static String LBL_SIGN_IN = "";
	public static String INF_SIGN_IN = "";
	public static String LBL_EXIT = "";
	public static String INF_EMTPY_PRODUCT = "";
	public static String LBL_PRICE_TYPE = "";
	public static String LBL_MORE = "";
	public static String LBL_DEBUG = "";
	public static String LBL_WAIT = "";
	public static String INF_NEVER_SHOW = "";
	public static String INF_DONT_SHOW = "";
	public static String LBL_YES = "";
	public static String LBL_NO = "";
	
	//=================new labels===================================================
	//public static String LBL_LIST_PRICE_SIMPLE = "";
	public static String INF_NO_PROFILE_DATA = "";
	public static String INF_GETTING_USER_INFO = "";
	
	public static String INF_GETTING_CATEGORIES= "";
	public static String INF_GETTING_PRODUCTS= "";
	public static String INF_NO_PRODUCTS_AVAILABLE = "";
	public static String INF_GETTING_INFO = "";
	public static String INF_GETTING_ADDRESSES = "";
	public static String INF_GETTING_CARRIERS = "";
	public static String INF_PROCESSING_ORDER = "";
	public static String INF_GETTING_MANIFACTURERS = "";
	public static String INF_GETTING_FAMILIES = "";
	public static String INF_LOGGING_IN ="";

	public static String INF_GETTING_CART = "";
	public static String INF_UPDATING_CART = "";
	public static String INF_CART_UPDATED = "";
	public static String INF_CART_NOT_UPDATED = "";
	
	public static String INF_SOMETHING_WRONG_RETRY ="";
	public static String LBL_NEWS_EVENTS = "";
	public static String LBL_PROMOTION = "";
	public static String LBL_PRODUCTS = "";
	public static String LBL_PRICE_SIMPLE = "";
	public static String INF_NO_NEWS_EVENTS = "";
	
	public static String LBL_TYPE_LETTERS_OF_BRAND = "";
	public static String LBL_QUICK_SEARCH = "";
	
	public static String LBL_PLEASE_ENTER_QUANTITY = "";
	
	public static String INF_LOADING = "";	
	public static String LBL_PRODUCTS_IN_CART = "";
	public static String LBL_TOTAL = "";
	
	public static String LBL_NAME_EN = "Name";
	public static String LBL_NAME_ES = "Nombre";
	public static String LBL_NAME_IT = "Nome";
	
	static {
		setEnglish();
	}
	private static void setEnglish() {
		LBL_LOGGING = "Signing in..."; // miss
		LBL_SIGNING_IN = "Signing in..."; // miss
		LBL_CANCELLED = "Cancelled!"; // miis
		LBL_RETRY = "Retry?"; // miss
		LBL_REFRESH = "Refresh"; // miss
		LBL_REFRESHING = " refreshing..."; // miss
		LBL_RETRYING = "retrying..."; // miss
		LBL_UPDATING = " updating..."; // miss
		LBL_OPERATION_FAILED = "Operation failed."; // miss
		LBL_BACK = "Back"; // miss
		LBL_SAVE = "Save"; // miss

		LBL_SELECT = "Select"; // <string name="action">Select Action</string>
								// ?? <string
								// name="search_manifacturer_title">Select the
								// search type"</string>
		LBL_CONTINUE = "Continue"; // miss
		LBL_CONFIRM = "Confirm"; // miss
		LBL_SEARCHING = "Searching";// "Searching..."; //miss,modified without
									// ...
		LBL_SEARCH = "Search";
		INF_GETTING_DETAILS = "Getting details..."; // miss

		INF_CONNECTION_TIMED_OUT = "";// "connection timed out"; <string
										// name="no_connection">Internet
										// connection is not available</string>
										// ?? <string
										// name="service_call_error">Service is
										// unavailable. Try again later</string>
		INF_UNABLE_TO_COMPLETE = "Unable to complete the operation."; // miss or
																		// same
																		// as
																		// INF_CONNECTION_TIMED_OUT
		INF_UPDATED = "Updated."; // miss

		INF_INVALID_DATA = "Invalid data!"; // <string
											// name="incorrect_credentials">Incorrect
											// username or password</string>
		LBL_SETTINGS = "Settings"; // miss
		LBL_HELP = "Help"; // miss
		LBL_HELP_ABOUT = "Help/About"; // miss

		LBL_FEEDBACK = "Feedback"; // miss
		LBL_BUGS_FEEDBACK = "Bugs/Feedback";// miss
		LBL_LOG_OUT = "Logout";
		INF_ARE_YOU_SURE_LOG_OUT = "Are you sure you want to log out?";// miss

		LBL_NAME = "Name";
		LBL_NEXT = "Next"; // Show the next 20 ?
		LBL_PASSWORD = "Password";
		LBL_SCHOOL = "School";// miss
		LBL_DESCRIPTION = "Description";
		LBL_CLICK_TO_CHANGE = "click to change";// miss
		INF_ARE_YOU_SURE_YOU_WANT_TO_REMOVE = "Are you sure that you want to remove "; // miss

		LBL_ADD = "Add"; // <string name="add_to_cart">Add to Cart</string>
		LBL_REMOVE = "Remove"; // <string name="delete">Delete</string>
		LBL_EDIT = "Edit"; // miss
		LBL_EMAIL = "Email"; // <string
								// name="profile_email_code">e-mail</string>
		LBL_EMAIL_ADDRESS = "Email Address"; // ==//==
		LBL_OK = "Ok";
		LBL_OTHER = "Other";// miss
		LBL_OTHERS = "Others";// miss
		LBL_TEXT = "Text";// miss
		LBL_CANCEL = "Cancel";
		LBL_DETAILS = "Details";
		LBL_DELETE = "Delete";
		LBL_SECTORS = "Sectors";// miss
		INF_NO_SECTORS = "There are no sectors available!"; //miss
		INF_NO_FAMILIES = "There are no families available!"; // miss

		INF_EXIT = "Are you sure you want to exit the application?"; // Do you
																		// really
																		// want
																		// to
																		// exit?

		LBL_CATEGORIES = "Categories";
		INF_NO_CATEGORIES = "No Categories!";
		INF_NO_PRODUCTS = "No Products!";
		
		INF_INVALID_CREDENTIALS = "Invalid username and password.";

		INF_NO_SEARCH_RESULTS = "No Results";//No Results!
		INF_NO_RESULTS = "No Results";//No Results!
		INF_NO_PRODUCT_DETAILS = "No Product Details are available!"; // <string
														// name="no_details">No
														// product details are
														// available</string>

		LBL_SEARCH_RESULTS = "Search Results";
		LBL_PRODUCT_DETAILS = "Product Details";
		LBL_FAVOURITES = "Favorites";

		LBL_CART = "Shopping Cart";

		LBL_IMAGE_GALLERY = "Image Gallery";
		LBL_TECHNICAL_DETAILS = "Technical Details";
		LBL_BRAND = "Brand";
		LBL_BRANDS = "Brands";
		LBL_AVAILABILITY = "Availability";
		LBL_RELATED_PRODUCTS = "Related Products";

		LBL_FEES = "Fees";
		LBL_TOTAL_PRICE_NO_VAT = "Total Price (no VAT)";
		LBL_TOTAL_PRICE_VAT = "Total Price (with VAT)";
		LBL_BUY = "Buy";
		LBL_HISTORY = "History";
		LBL_ADD_TO_FAVORITES = "Add To Favorites";
		LBL_MORE_DETAILS = "More";
		LBL_ADDITIONAL_DETAILS = "Additional Details";
		// public final static String LBL_TECHNICAL_DETAILS =
		// "Technical Details";

		EURO_SIGN = "\u20AC";
		STAR_SIGN = "\u22C6";// STAR OPERATOR
								// //http://www.fileformat.info/info/unicode/char/22c6/index.htm
		STAR_SIGN_2 = "\u2605";// black star
		STAR_SIGN_3 = "\u066D";// arabic five pointed star
		STAR_SIGN_4 = "\u2B50";// white medium star U+2B50
		STAR_SIGN_5 = "\u2606";// http://en.wikipedia.org/wiki/Miscellaneous_Symbols

		EURO_SIGN_BRACKETS = "(\u20AC)";

		LBL_EAN = "EAN";
		LBL_CODE = "Code";
		LBL_MANUF_CODE = "Manuf. Code";
		LBL_MANUF_WARRANTY = "Manuf. warranty";
		//LBL_PRICE = "Price " + EURO_SIGN_BRACKETS;
		LBL_LIST_PRICE = "List Price";// + EURO_SIGN_BRACKETS;
//		LBL_END_PRICE = "End Price " + EURO_SIGN_BRACKETS;
		LBL_END_USER_WARRANTY = "End User Warranty";

		//INF_ADDED_TO_FAVORITES = "Added to favorites.";
		//INF_ALREADY_IN_FAVORITES = "The product is already in favorites";

		INF_NO_FAVORITE_PRODUCTS = "No Favorite Products!";
		INF_NO_HISTORY_PRODUCTS = "No Products in the History!";

		LBL_FAMILIES = "Families";
		LBL_ADDRESSES = "Delivery address";
		INF_NO_ADDRESSES = "You don\'t have any shipping addresses";
		//LBL_CARIERS = "Carrier";
		INF_NO_CARIERS = "You don\'t have any carriers";

		LBL_CARRIER = "Carrier";
		LBL_ADDRESS = "Address";
		LBL_ORDER_SUMMARY = "Order Summary";
		INF_ORDER_SUCCESS = "Order was completed successfully";
		INF_ORDER_PROBLEM = "Order was not completed successfully";
		LBL_GALLERY = "Gallery";

		LBL_CODE_SMALL = "code";
		LBL_AVAILABILITY_SMALL = "availability";

		INF_ENTER_VALID_QUANTITY = "Please enter a valid quantity!";
		INF_NO_AVAILABILITY = "No availability from this product!";
		INF_NO_ENOUGH_QUANTITY = "Not enough quantity!";

		LBL_QUANTITY = "Quantity";

		LBL_ADD_TO_CART = "Add to Cart";
		// public final static String LBL_ADD_TO_FAVORITES = "Add to Favorites";
		// public final static String LBL_ADD_TO_CART = "Add to Cart";
		// public final static String LBL_ADD_TO_CART = "Add to Cart";
		// public final static String LBL_ADD_TO_CART = "Add to Cart";

		LBL_EVENTS = "Events";
		LBL_PROFILE = "Profile";
		LBL_CUSTOMER_CODE = "CustomerCode";
		LBL_MAIL = "e-mail";
		LBL_USER_CODE = "UserCode";
		LBL_LANGUAGE = "Language";
		LBL_PRICE_TAB = "Price tab";

		LBL_START_DATE = "Start Date: ";
		LBL_END_DATE = "End Date: ";

		LBL_ENGLISH = "English";
		// public final static String LBL_RELATED_PRODUCTS = "Related Products";
		LBL_SPANISH = "Spanish";
		LBL_ITALIAN = "Italian";

		LBL_YOUR_PRICE = "Your Price";

		LBL_PROMOTIONS = "Promotions";
		INF_NO_PROMOTIONS = "No Promotions!";

		LBL_IN_THE_FUTURE = "In the Future";
		LBL_DAYSAGO = "Days Ago";
		LBL_YESTERDAY = "Yesterday";
		LBL_TODAY = "Today";
		LBL_1YEARAGO = "One Year Ago";
		LBL_YEARSAGO = "Years ago";
		LBL_1MONTHAGO = "1 Month Ago";
		LBL_MONTHSAGO = "Months Ago";
		LBL_1WEEKAGO = "1 Week Ago";
		LBL_2WEEKSAGO = "2 Weeks Ago";
		LBL_3WEEKSAGO = "3 Weeks Ago";

		LBL_GOTOPRODUCTS = "Search products in promo";
		// public final static String LBL_RELATED_PRODUCTS =
		// "Products Products";

		LBL_ALL_BRANDS = "All Brands";
		LBL_BRANDS_BY_LETTER = "Brands by Letter";
		LBL_SELECT_SEARCH_TYPE = "Select search type";

		INF_UNABLE_TO_CONNECT = "Unable to connect to the service. Retry?";
		INF_REMOVE_VERIFICATION = "Are you sure you want to remove this product from the cart?";
		LBL_GET_DETAILS = "Get Details";
		LBL_GALLERY_TITLE = "Gallery";
		LBL_SIGN_IN = "Sign in";
		INF_SIGN_IN = "Enter your username.";
		LBL_EXIT = "Exit";
		INF_EMTPY_PRODUCT = "The product is empty!";
		LBL_PRICE_TYPE = "Price Type";
		LBL_MORE = "More";
		LBL_DEBUG = "Debug";
		LBL_WAIT = "Please wait...";
		INF_NEVER_SHOW = "Never show this message again.";
		INF_DONT_SHOW = "Do not show this message again.";
		LBL_YES = "Yes";
		LBL_NO = "No";

		// =================new
		// labels===================================================
		//LBL_LIST_PRICE_SIMPLE = "List Price";
		INF_NO_PROFILE_DATA = "Unable to get your profile data.";
		INF_GETTING_USER_INFO = "Getting user info";

		INF_GETTING_CATEGORIES = "Getting categories";
		INF_GETTING_PRODUCTS = "Getting products";
		INF_NO_PRODUCTS_AVAILABLE = "No products available";
		INF_GETTING_INFO = "Getting information";
		INF_GETTING_ADDRESSES = "Getting addresses";
		INF_GETTING_CARRIERS = "Getting carriers";
		INF_PROCESSING_ORDER = "Processing order";
		INF_GETTING_MANIFACTURERS = "Getting manifacturers";
		INF_GETTING_FAMILIES = "Getting families";
		INF_LOGGING_IN = "Logging in";
		INF_GETTING_CART = "Getting cart";
		INF_UPDATING_CART = "Updating cart";
		INF_CART_UPDATED = "Cart updated!";
		INF_CART_NOT_UPDATED = "Cart not updated!";

		INF_SOMETHING_WRONG_RETRY = "Something went wrong! Please retry!\n";
		LBL_NEWS_EVENTS = "News/Events";
		LBL_PROMOTION = "Promotion";
		LBL_PRODUCTS = "Products";
		LBL_PRICE_SIMPLE = "Price";
		INF_NO_NEWS_EVENTS = "No news/events!";

		LBL_TYPE_LETTERS_OF_BRAND = "Type the first letters of a brand";
		LBL_QUICK_SEARCH = "Quick search";

		LBL_PLEASE_ENTER_QUANTITY = "Please Enter a quantity";
		
		INF_LOADING = "Loading... Please wait";
		
		LBL_PRODUCTS_IN_CART = "Products in Cart";
		LBL_TOTAL = "Total";
		
	}
	private static void setItalian() {
		LBL_LANGUAGE = "Lingua";
		LBL_NAME = LBL_NAME_IT; //<string name="LoginController.UserName">Utente</string> 
		LBL_SIGN_IN = "Accedi";
		INF_SIGN_IN = "Inserire il Nome Utente";
		LBL_SIGNING_IN = "Accesso in corso...";
		LBL_LOGGING = "Accesso in corso...";
		INF_INVALID_DATA = "Username o Password errata"; //in english INF_INVALID_DATA = "Invalid data!"
		LBL_EVENTS = "Evento";
		LBL_FAVOURITES = "Preferiti";
		LBL_HISTORY = "Cronologia";
		INF_NO_HISTORY_PRODUCTS = "Cronologia assente";
		LBL_NEWS_EVENTS = "Novità/Eventi";
		LBL_SEARCH = "Ricerca";
		LBL_ALL_BRANDS = "Tutti i produttori";
		LBL_BRANDS_BY_LETTER = "Produttori per lettera iniziale";
		INF_ORDER_SUCCESS = "Ordine completato correttamente";
		INF_ORDER_PROBLEM = "Impossibile completare l\'ordine";
		LBL_PROMOTIONS = "Promozione";
		INF_NO_PROMOTIONS = "Nessuna promozione in corso";
		LBL_START_DATE = "Data inizio :";
		LBL_END_DATE = "Data fine :";
		LBL_SEARCH_RESULTS = "Risultati ricerca";
		LBL_CART = "Carrello";
		LBL_PROFILE = "Profilo Utente";
		LBL_PRICE_TAB = "Prezzo nella scheda:";
		LBL_LIST_PRICE = "Prezzo Listino";// + EURO_SIGN_BRACKETS;
		LBL_YOUR_PRICE = "Il tuo prezzo";
		LBL_PRODUCTS = "Prodotti";
		LBL_PRODUCT_DETAILS = "Scheda Prodotto";
		LBL_DESCRIPTION = "Descrizione";
		LBL_IMAGE_GALLERY = "Galleria Immagini";
		LBL_AVAILABILITY = "Disponibilità";
		LBL_DETAILS = "Dettagli prodotto";
		INF_NO_PRODUCT_DETAILS = "I dettagli del prodotto non sono disponibili";
		LBL_YES = "Si";
		LBL_NO = "No";
		LBL_RELATED_PRODUCTS = "Aggiungi alla vendita anche...";
		LBL_ADD_TO_CART = "Aggiungi";
		LBL_CANCEL = "Esci";
		INF_UNABLE_TO_CONNECT = "Impossibile connettersi al server. Riprova più tardi";
		LBL_BACK = "Indietro";
		LBL_LOG_OUT = "Esci";
		LBL_BUY = "Compra";
		INF_NO_SEARCH_RESULTS = "Nessun risultato";
		INF_NO_RESULTS = INF_NO_SEARCH_RESULTS;
		INF_INVALID_CREDENTIALS = "Username o Password errata";
		LBL_BRAND = "Produttore";
		LBL_BRANDS = "Produttori";
		LBL_CATEGORIES = "Categorie";
		LBL_YESTERDAY = "Ieri";
		LBL_TODAY = "Oggi";
		LBL_1YEARAGO = "Un anno fa";
		LBL_1MONTHAGO = "1 mese fa";
		LBL_1WEEKAGO = "1 Settimana fa";
		LBL_2WEEKSAGO = "2 Settimane fa";
		LBL_3WEEKSAGO = "3 Settimane fa";
		
		LBL_YEARSAGO = "anni fa";
		LBL_MONTHSAGO = "mesi fa";
		LBL_DAYSAGO = "Giorni fa";
		
		
		INF_LOADING = "Loading... Please wait";
		
		LBL_ADDRESSES = "Indirizzo per la consegna";
		LBL_CARRIER = "Selezione Corriere";
		
		INF_NO_CARIERS = "Nessun corriere selezionabile";
		INF_NO_ADDRESSES = "Indirizzi per la consegna assenti : inseriscili dal portale";
		
		INF_NO_NEWS_EVENTS = "Nessun evento in corso";
		
		LBL_GALLERY = "Galleria";
		LBL_ADDITIONAL_DETAILS = "Dettagli prodotto";
		LBL_TECHNICAL_DETAILS = "Scheda technica";
		LBL_MORE_DETAILS = "Di più";
		LBL_PRODUCTS_IN_CART = "Prodotti nel carrello";
		LBL_TOTAL = "Totale";
		
		LBL_FEES = "Compensi";
		LBL_TOTAL_PRICE_NO_VAT = "Totale (IVA esclusa)";
		LBL_TOTAL_PRICE_VAT = "Totale (IVA inclusa)";
	}

	private static void setSpanish() {
		LBL_LANGUAGE = "Lengua";
		LBL_NAME = LBL_NAME_ES; //<string name="LoginController.UserName">Utente</string> 
		LBL_SIGN_IN = "Acceso";
		//INF_SIGN_IN = "Inserire il Nome Utente";
		//LBL_SIGNING_IN = "Accesso in corso...";
		LBL_LOGGING = "Acceso en curso...";
		INF_INVALID_DATA = "Usuario o Contraseña erroneos"; //in english INF_INVALID_DATA = "Invalid data!"
		LBL_EVENTS = "Eventos";
		LBL_FAVOURITES = "Favoritos";
		LBL_HISTORY = "Cronologia";
		INF_NO_HISTORY_PRODUCTS = "Ninguna Cronología";
		LBL_NEWS_EVENTS = "Novedades/Eventos";
		LBL_SEARCH = "Búsqueda";
		LBL_ALL_BRANDS = "Todos los fabricantes";
		LBL_BRANDS_BY_LETTER = "Fabricante por letra inicial";
		INF_ORDER_SUCCESS = "Pedido Completado correctamente";
		INF_ORDER_PROBLEM = "Imposible completar el pedido";
		LBL_PROMOTIONS = "Promociónes";
		INF_NO_PROMOTIONS = "Ninguna promoción activa";
		LBL_START_DATE = "Fecha inicio :";
		LBL_END_DATE = "Fecha fin :";
		LBL_SEARCH_RESULTS = "Resultado búsqueda";
		LBL_CART = "Carrito";
		LBL_PROFILE = "Perfil Usuario";
		LBL_PRICE_TAB = "Precio en la ficha:";
		LBL_LIST_PRICE = "PVP";// + EURO_SIGN_BRACKETS;
		LBL_YOUR_PRICE = "Su precio";
		LBL_PRODUCTS = "Productos";
		LBL_PRODUCT_DETAILS = "Ficha de Producto";
		LBL_DESCRIPTION = "Descripción";
		LBL_IMAGE_GALLERY = "Galería de Imágenes";
		LBL_AVAILABILITY = "Disponibilidad";
		LBL_DETAILS = "Detalle Producto";
		INF_NO_PRODUCT_DETAILS = "Los detalles del producto no son disponibles";
		LBL_YES = "Sí";
		LBL_NO = "No";
		LBL_RELATED_PRODUCTS = "Productos complementarios";
		LBL_ADD_TO_CART = "Añadir";
		LBL_CANCEL = "Salida";
		INF_UNABLE_TO_CONNECT = "Sin conexión a Internet";
		LBL_BACK = "Atrás";
		LBL_LOG_OUT = "Salida";
		LBL_BUY = "Comprar";
		INF_NO_SEARCH_RESULTS = "Sin resultados";
		INF_NO_RESULTS = INF_NO_SEARCH_RESULTS;
		INF_INVALID_CREDENTIALS = "Usuario o Contraseña erroneo";
		LBL_BRAND = "Fabricante";
		LBL_BRANDS = "Fabricantes";
		LBL_CATEGORIES = "Categorías";
		LBL_YESTERDAY = "Ayer";
		LBL_TODAY = "Hoy";
		LBL_1YEARAGO = "1 año atrás";
		LBL_1MONTHAGO = "1 mes atrás";
		LBL_1WEEKAGO = "1 Semana atrás";
		LBL_2WEEKSAGO = "2 Semanas atrás";
		LBL_3WEEKSAGO = "3 Semanas atrás";
		
		LBL_YEARSAGO = "años atrás";
		LBL_MONTHSAGO = "meses atrás";
		LBL_DAYSAGO = "dias atrás";
		
		
		INF_LOADING = "Loading... Please wait";
		
		LBL_ADDRESSES = "Dirección para la entrega";
		LBL_CARRIER = "Seleccione un transportista";
		INF_NO_CARIERS = "Ningun transportista seleccionable";
		INF_NO_ADDRESSES = "Ninguna dirección para la entrega: insertalo a traves del portal web";
		
		INF_NO_NEWS_EVENTS = "Ningún evento en curso";
		LBL_PRODUCTS_IN_CART = "Producto en el carrito";
		LBL_TOTAL = "Total";
		
		LBL_FEES = "Total Tasas";
		LBL_TOTAL_PRICE_NO_VAT = "Total (sin IVA)";
		LBL_TOTAL_PRICE_VAT = "Total (con IVA)";
	}
}
