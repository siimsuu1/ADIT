package ee.adit.web.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import ee.adit.service.MonitorService;
import ee.adit.util.Configuration;
import ee.adit.util.MonitorConfiguration;
import ee.adit.util.MonitorResult;

public class MonitorController extends AbstractController {

	private static Logger LOG = Logger.getLogger(MonitorController.class);
	
	private MonitorService monitorService;
	
	private Configuration configuration;
	
	public MonitorController() {
		LOG.info("MONITORCONTROLLER created.");
	}
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		
		String requestURI = arg0.getRequestURI();
		String serverName = arg0.getServerName();
		int serverPort = arg0.getServerPort();
		LOG.debug("requestURI: " + requestURI);
		LOG.debug("serverName: " + serverName);
		LOG.debug("serverPort: " + serverPort);
		
		// http://locahost:8080/adit/monitor
		
		String serviceURI = "http://" + serverName + ":" + serverPort + "/adit/service";
		
		LOG.info("ADIT monitoring servlet invoked.");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("monitor.jsp");
		DecimalFormat df = new DecimalFormat("0.000");
		
		List<MonitorResult> results = new ArrayList<MonitorResult>();
		boolean summaryStatusOk = true;
		
		try {
		
			double duration = 0;
			Date start = new Date();
			long startTime = start.getTime();
			
			if(getMonitorService() == null)
				LOG.error("getMonitorService() == null");
			if(getConfiguration() == null)
				LOG.error("getConfiguration() == null");
			
			// 1. SAVE_DOCUMENT
			MonitorResult saveDocumentCheckResult = this.getMonitorService().saveDocumentCheck(serviceURI);
			saveDocumentCheckResult.setDurationString(df.format(saveDocumentCheckResult.getDuration()));
			if(saveDocumentCheckResult.isSuccess()) {
				saveDocumentCheckResult.setStatusString(MonitorService.OK);
			} else {
				saveDocumentCheckResult.setStatusString(MonitorService.FAIL);
				summaryStatusOk = false;
			}
			if(saveDocumentCheckResult.getExceptions() != null && saveDocumentCheckResult.getExceptions().size() > 0) {
				saveDocumentCheckResult.setExceptionString(saveDocumentCheckResult.getExceptions().get(0));
			}
			results.add(saveDocumentCheckResult);
			
			// 2. GET_DOCUMENT
			MonitorResult getDocumentCheckResult = this.getMonitorService().getDocumentCheck(serviceURI);
			getDocumentCheckResult.setDurationString(df.format(getDocumentCheckResult.getDuration()));
			if(getDocumentCheckResult.isSuccess()) {
				getDocumentCheckResult.setStatusString(MonitorService.OK);
			} else {
				getDocumentCheckResult.setStatusString(MonitorService.FAIL);
				summaryStatusOk = false;
			}
			if(getDocumentCheckResult.getExceptions() != null && getDocumentCheckResult.getExceptions().size() > 0) {
				getDocumentCheckResult.setExceptionString(getDocumentCheckResult.getExceptions().get(0));
			}
			results.add(getDocumentCheckResult);
			
			// 3. DVK_SEND
			MonitorResult dvkSendCheckResult = this.getMonitorService().checkDvkSend();
			dvkSendCheckResult.setDurationString(df.format(dvkSendCheckResult.getDuration()));
			if(dvkSendCheckResult.isSuccess()) {
				dvkSendCheckResult.setStatusString(MonitorService.OK);
			} else {
				dvkSendCheckResult.setStatusString(MonitorService.FAIL);
				summaryStatusOk = false;
			}
			if(dvkSendCheckResult.getExceptions() != null && dvkSendCheckResult.getExceptions().size() > 0) {
				dvkSendCheckResult.setExceptionString(dvkSendCheckResult.getExceptions().get(0));
			}
			results.add(dvkSendCheckResult);
			
			// 3. DVK_RECEIVE
			MonitorResult checkDvkReceiveResult = this.getMonitorService().checkDvkReceive();
			checkDvkReceiveResult.setDurationString(df.format(checkDvkReceiveResult.getDuration()));
			if(checkDvkReceiveResult.isSuccess()) {
				checkDvkReceiveResult.setStatusString(MonitorService.OK);
			} else {
				checkDvkReceiveResult.setStatusString(MonitorService.FAIL);
				summaryStatusOk = false;
			}
			if(checkDvkReceiveResult.getExceptions() != null && checkDvkReceiveResult.getExceptions().size() > 0) {
				checkDvkReceiveResult.setExceptionString(checkDvkReceiveResult.getExceptions().get(0));
			}
			results.add(checkDvkReceiveResult);
			
			
			// 4. GET_USER_INFO
			MonitorResult getUserInfoCheckResult = this.getMonitorService().getUserInfoCheck(serviceURI);
			getUserInfoCheckResult.setDurationString(df.format(getUserInfoCheckResult.getDuration()));
			if(getUserInfoCheckResult.isSuccess()) {
				getUserInfoCheckResult.setStatusString(MonitorService.OK);
			} else {
				getUserInfoCheckResult.setStatusString(MonitorService.FAIL);
				summaryStatusOk = false;
			}
			if(getUserInfoCheckResult.getExceptions() != null && getUserInfoCheckResult.getExceptions().size() > 0) {
				getUserInfoCheckResult.setExceptionString(getUserInfoCheckResult.getExceptions().get(0));
			}
			results.add(getUserInfoCheckResult);
			
			// 5. NOTIFICATIONS
			MonitorResult checkNotificationsResult = this.getMonitorService().checkNotifications();
			checkNotificationsResult.setDurationString(df.format(checkNotificationsResult.getDuration()));
			if(checkNotificationsResult.isSuccess()) {
				checkNotificationsResult.setStatusString(MonitorService.OK);
			} else {
				checkNotificationsResult.setStatusString(MonitorService.FAIL);
				summaryStatusOk = false;
			}
			if(checkNotificationsResult.getExceptions() != null && checkNotificationsResult.getExceptions().size() > 0) {
				checkNotificationsResult.setExceptionString(checkNotificationsResult.getExceptions().get(0));
			}
			results.add(checkNotificationsResult);
			
			// 6. ERROR_LOG
			MonitorResult checkErrorLogResult = this.getMonitorService().checkErrorLog();
			checkErrorLogResult.setDurationString(df.format(checkErrorLogResult.getDuration()));
			if(checkErrorLogResult.isSuccess()) {
				checkErrorLogResult.setStatusString(MonitorService.OK);
			} else {
				checkErrorLogResult.setStatusString(MonitorService.FAIL);
			}
			if(checkErrorLogResult.getExceptions() != null && checkErrorLogResult.getExceptions().size() > 0) {
				checkErrorLogResult.setExceptionString(checkErrorLogResult.getExceptions().get(0));
			}
			results.add(checkErrorLogResult);

			Date end = new Date();
			long endTime = end.getTime();
			duration = (endTime - startTime) / 1000.0;
			
			// 7. SUMMARY_STATUS
			MonitorResult summaryStatusResult = new MonitorResult();
			summaryStatusResult.setComponent("SUMMARY_STATUS");
			summaryStatusResult.setDurationString(df.format(duration));
			summaryStatusResult.setExceptionString(df.format(duration));
			summaryStatusResult.setSuccess(summaryStatusOk);
			
			if(summaryStatusOk) {
				summaryStatusResult.setStatusString(MonitorService.OK);
			} else {
				summaryStatusResult.setStatusString(MonitorService.FAIL);
			}
			
			results.add(summaryStatusResult);
		
		} catch (Exception e) {
			LOG.error("Error while invoking monitoring controller: ", e);
		}
		
		mav.addObject("results", results);
		
		return mav;
	}

	public MonitorService getMonitorService() {
		return monitorService;
	}

	public void setMonitorService(MonitorService monitorService) {
		LOG.info("Setting MONITORSERVICE on MONITORCONTROLLER");
		this.monitorService = monitorService;
	}

	public Configuration getConfiguration() {
		LOG.info("Setting CONFIGURATION on MONITORCONTROLLER");
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
}
