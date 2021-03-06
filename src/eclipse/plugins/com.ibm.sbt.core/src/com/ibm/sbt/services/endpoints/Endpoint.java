/*
 * � Copyright IBM Corp. 2012
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */

package com.ibm.sbt.services.endpoints;

import java.util.Map;

import org.apache.http.impl.client.DefaultHttpClient;

import com.ibm.sbt.services.client.ClientService;
import com.ibm.sbt.services.client.ClientService.Args;
import com.ibm.sbt.services.client.ClientService.Handler;
import com.ibm.sbt.services.client.ClientServicesException;
import com.ibm.sbt.services.endpoints.js.JSReference;
import com.ibm.sbt.util.SBTException;


/**
 * Service end point.
 * <p>
 * </p>
 * @author Philippe Riand
 */
public interface Endpoint {
	
    //
    // Access to the server base URL
    //
    
    /**
     * Get the url used to access the server.
     * @return the URL string
     */
    public String getUrl();

    /**
     * Get the user identity if available.
     * Note that the identity is not always available, for example when using
     * an cookie based or an OAuth endpoint. This is mostly for basic authentication
     * where the user name is known.
     * @return the user identity, or null if not available 
     */
    public String getUserIdentity() throws ClientServicesException;
    
    /**
     * Get the proxy path for this endpoint.
     * This path should be dynamically added to the proxy URL
     */
    public String getProxyPath(String endpointName);
    
    /**
     * TODO Can this be combined with the proxyPath
     * 
     * @return
     */
    public String getProxyHandlerPath();
    
    /**
     * Get the client authenticator to use, if any.
     */
    public JSReference getAuthenticator(String endpointName);
    
    /**
     * Get the client transport to use, if any.
     */
    public JSReference getTransport(String endpointName);
    
    /**
     * Get the endpoint label, for the login dialog. 
     * @return
     */
    public String getLabel();
    
    /**
     * 
     * @return
     */
    public boolean isAllowClientAccess();
    
    /**
     * Get page for the login popup and main window. 
     * @return
     */
    public String getLoginPage();
    
    /**
     * Get page for the login dialog. 
     * @return
     */
    public String getDialogLoginPage();
    
    /**
     * Get login UI mode. 
     * @return
     */
    public String getLoginUi();
    

    /**
     * Get auth type for the endpoint. 
     * @return
     */
    public String getAuthType();
    
    /**
     * Verifies whether the given header is supported by the endpoint
     * @param headerName name of the header
     * @return boolean
     */
    public boolean isHeaderAllowed(String headerName, String serviceUrl);
    
    /**
     * Return true if endpoint requires authentication.
     * 
     * @return
     * @throws ClientServicesException
     */
    public boolean isRequiresAuthentication() throws ClientServicesException;
    
    /**
     * Return true if endpoint is autenticated.
     * 
     * @return
     * @throws ClientServicesException
     */
    public boolean isAuthenticated() throws ClientServicesException;

    /**
     * Return true if authentication is valid.
     * 
     * @return
     * @throws ClientServicesException
     */
    public boolean isAuthenticationValid() throws ClientServicesException;
    
    /**
     * Invoked when authentication for this endpoint is required.
     * 
     * @param force
     * @throws ClientServicesException
     */
    public void authenticate(boolean force) throws ClientServicesException;
    
    /**
     * Invoked to initialize this endpoint.
     * 
     * @param httpClient
     * @throws ClientServicesException
     */
    public void initialize(DefaultHttpClient httpClient) throws ClientServicesException;

    /**
     * 
     * @throws SBTException
     */
    public void checkValid() throws SBTException;
       
    /**
     * 
     * @param method
     * @param args
     * @param content
     * @return
     * @throws ClientServicesException
     */
    public Object xhr(String method, ClientService.Args args, Object content) throws ClientServicesException;
    
    /**
     * 
     * @param serviceUrl
     * @return
     * @throws ClientServicesException
     */
    public Object xhrGet(String serviceUrl) throws ClientServicesException;
    
    /**
     * 
     * @param serviceUrl
     * @param parameters
     * @return
     * @throws ClientServicesException
     */
    public Object xhrGet(String serviceUrl, Map<String, String> parameters) throws ClientServicesException;
    
    /**
     * 
     * @param serviceUrl
     * @param parameters
     * @param format
     * @return
     * @throws ClientServicesException
     */
    public Object xhrGet(String serviceUrl, Map<String, String> parameters, Handler format) throws ClientServicesException;
    
    /**
     * 
     * @param args
     * @return
     * @throws ClientServicesException
     */
    public Object xhrGet(Args args) throws ClientServicesException;
    
    /**
     * 
     * @param serviceUrl
     * @param content
     * @return
     * @throws ClientServicesException
     */
    public Object xhrPost(String serviceUrl, Object content) throws ClientServicesException;
    
    /**
     * 
     * @param serviceUrl
     * @param parameters
     * @param content
     * @return
     * @throws ClientServicesException
     */
    public Object xhrPost(String serviceUrl, Map<String, String> parameters, Object content) throws ClientServicesException;
    
    /**
     * 
     * @param serviceUrl
     * @param parameters
     * @param content
     * @param format
     * @return
     * @throws ClientServicesException
     */
    public Object xhrPost(String serviceUrl, Map<String, String> parameters, Object content, Handler format) throws ClientServicesException;
    
    /**
     * 
     * @param args
     * @param content
     * @return
     * @throws ClientServicesException
     */
    public Object xhrPost(Args args, Object content) throws ClientServicesException;

    /**
     * 
     * @param serviceUrl
     * @param content
     * @return
     * @throws ClientServicesException
     */
    public Object xhrPut(String serviceUrl, Object content) throws ClientServicesException;
    
    /**
     * 
     * @param serviceUrl
     * @param parameters
     * @param content
     * @return
     * @throws ClientServicesException
     */
    public Object xhrPut(String serviceUrl, Map<String, String> parameters, Object content) throws ClientServicesException;
    
    /**
     * 
     * @param serviceUrl
     * @param parameters
     * @param content
     * @param format
     * @return
     * @throws ClientServicesException
     */
    public Object xhrPut(String serviceUrl, Map<String, String> parameters, Object content, Handler format) throws ClientServicesException;
    
    /**
     * 
     * @param args
     * @param content
     * @return
     * @throws ClientServicesException
     */
    public Object xhrPut(Args args, Object content) throws ClientServicesException;

    /**
     * 
     * @param serviceUrl
     * @return
     * @throws ClientServicesException
     */
    public Object xhrDelete(String serviceUrl) throws ClientServicesException;
    
    /**
     * 
     * @param serviceUrl
     * @param parameters
     * @return
     * @throws ClientServicesException
     */
    public Object xhrDelete(String serviceUrl, Map<String, String> parameters) throws ClientServicesException;
    
    /**
     * 
     * @param serviceUrl
     * @param parameters
     * @param format
     * @return
     * @throws ClientServicesException
     */
    public Object xhrDelete(String serviceUrl, Map<String, String> parameters, Handler format) throws ClientServicesException;
    
    /**
     * 
     * @param args
     * @return
     * @throws ClientServicesException
     */
    public Object xhrDelete(Args args) throws ClientServicesException;
    
    /**
     * 
     * @return
     * @throws ClientServicesException
     */
    public ClientService getClientService() throws ClientServicesException;
    
    //
    // Manage the server credential
    //
    
    /**
     * This is to be used in development mode only, when SSL certificates for the server are not available.
     */
    public boolean isForceTrustSSLCertificate();
}
