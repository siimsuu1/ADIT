package ee.adit.service;

import org.apache.log4j.Logger;

import ee.adit.dao.AditUserDAO;
import ee.adit.dao.RemoteApplicationDAO;
import ee.adit.dao.UsertypeDAO;
import ee.adit.dao.pojo.AditUser;
import ee.adit.dao.pojo.RemoteApplication;
import ee.adit.dao.pojo.Usertype;

public class UserService {

	private static Logger LOG = Logger.getLogger(UserService.class);
	
	private RemoteApplicationDAO remoteApplicationDAO;
	
	private UsertypeDAO usertypeDAO;
	
	private AditUserDAO aditUserDAO;
	
	private static final String USERTYPE_PERSON = "PERSON";
	private static final String USERTYPE_INSTITUTION = "INSTITUTION";
	private static final String USERTYPE_COMPANY = "COMPANY";
	
	public boolean isApplicationRegistered(String remoteApplicationShortName) {
		boolean result = false;
		LOG.debug("Checking if application '" + remoteApplicationShortName + "' is registered.");
		
		if(this.getRemoteApplicationDAO() == null) {
			LOG.error("remoteApplicationDAO not initialized");
		} else {
			RemoteApplication remoteApplication = this.getRemoteApplicationDAO().getByShortName(remoteApplicationShortName);
			
			if(remoteApplication != null) {
				result = true;
			}
		}
		
		LOG.debug("Application '" + remoteApplicationShortName + "' is registered?: " + result);
		return result;
	}

	/**
	 * Determines the access level for this application:
	 * 0 - no access
	 * 1 - read access
	 * 2 - write acces (full access) 
	 * @return
	 */
	public int getAccessLevel(String remoteApplicationShortName) {
		int result = 0;
		
		RemoteApplication remoteApplication = this.getRemoteApplicationDAO().getByShortName(remoteApplicationShortName);
		if(remoteApplication != null) {
			if(remoteApplication.getCanWrite()) {
				result = 2;
			} else if(remoteApplication.getCanRead()) {
				result = 1;
			}
		}
		
		return result;
	}
	
	public Usertype getUsertypeByID(String usertypeShortName) {
		Usertype result = null;
		
		try {
			result = this.getUsertypeDAO().getByShortName(usertypeShortName);
		} catch (Exception e) {
			LOG.error("Error while fetching Usertype by sgort name: ", e);
		}
		
		return result;
	}
	
	public AditUser getUserByID(String userRegCode) {
		AditUser result = null;
		
		try {
			result = this.getAditUserDAO().getUserByID(userRegCode);
		} catch (Exception e) {
			LOG.error("Error while fetching AditUser by ID: ", e);
		}
		
		return result;
	}
	
	public void addUser(String username, Usertype usertype, String institutionCode, String personalCode) {
		if(USERTYPE_PERSON.equalsIgnoreCase(usertype.getShortName()) || USERTYPE_INSTITUTION.equalsIgnoreCase(usertype.getShortName()) || USERTYPE_COMPANY.equalsIgnoreCase(usertype.getShortName())) {
			addUser(username, personalCode, usertype);
		} else {
			// TODO: throw exception - unknown usertype
		}
	}
	
	public void addUser(String username, String usercode, Usertype usertype) {
		AditUser aditUser = new AditUser();
		aditUser.setUserCode(usercode);
		aditUser.setFullName(username);
		aditUser.setUsertype(usertype);
		this.getAditUserDAO().saveOrUpdate(aditUser);
	}
	
	public RemoteApplicationDAO getRemoteApplicationDAO() {
		return remoteApplicationDAO;
	}

	public void setRemoteApplicationDAO(RemoteApplicationDAO remoteApplicationDAO) {
		this.remoteApplicationDAO = remoteApplicationDAO;
	}

	public UsertypeDAO getUsertypeDAO() {
		return usertypeDAO;
	}

	public void setUsertypeDAO(UsertypeDAO usertypeDAO) {
		this.usertypeDAO = usertypeDAO;
	}

	public AditUserDAO getAditUserDAO() {
		return aditUserDAO;
	}

	public void setAditUserDAO(AditUserDAO aditUserDAO) {
		this.aditUserDAO = aditUserDAO;
	}
	
	
}
