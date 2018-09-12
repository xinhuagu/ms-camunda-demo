package de.berlin.home.kang.webrest;

import static org.camunda.bpm.engine.authorization.Authorization.ANY;
import static org.camunda.bpm.engine.authorization.Authorization.AUTH_TYPE_GRANT;
import static org.camunda.bpm.engine.authorization.Permissions.ACCESS;
import static org.camunda.bpm.engine.authorization.Permissions.ALL;
import static org.camunda.bpm.engine.authorization.Permissions.READ;
import static org.camunda.bpm.engine.authorization.Resources.APPLICATION;
import static org.camunda.bpm.engine.authorization.Resources.USER;

import java.io.Serializable;

import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.Groups;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resource;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.persistence.entity.AuthorizationEntity;

/**
 * 
 * Creates demo credentials to be used in the showcase.
 * 
 * some codes are copied from invoice example
 *
 * @author xinhua
 */
public class DemoDataGenerator implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5463428134599725645L;
	
	
	/**
	 * create demo account 'xinhua' with admin rights
	 * @param engine
	 */
    public void createUsers(final ProcessEngine engine) {

      final IdentityService identityService = engine.getIdentityService();

      if(identityService.isReadOnly()) {
        return;
      }

      User singleResult = identityService.createUserQuery().userId("xinhua").singleResult();
      
      if (singleResult != null) {
        return;
      }


      User user = identityService.newUser("xinhua");
      user.setFirstName("Xinhua");
      user.setLastName("Gu");
      user.setPassword("xinhua");
      user.setEmail("xinhua@gmail.com");
      identityService.saveUser(user);

      Group managementGroup = identityService.newGroup("WEB-management");
      managementGroup.setName("WEB-Management");
      managementGroup.setType("WORKFLOW");
      identityService.saveGroup(managementGroup);

      final AuthorizationService authorizationService = engine.getAuthorizationService();

      // create group
      if(identityService.createGroupQuery().groupId(Groups.CAMUNDA_ADMIN).count() == 0) {
        Group camundaAdminGroup = identityService.newGroup(Groups.CAMUNDA_ADMIN);
        camundaAdminGroup.setName("camunda BPM Administrators");
        camundaAdminGroup.setType(Groups.GROUP_TYPE_SYSTEM);
        identityService.saveGroup(camundaAdminGroup);
      }

      // create ADMIN authorizations on all built-in resources
      for (Resource resource : Resources.values()) {
        if(authorizationService.createAuthorizationQuery().groupIdIn(Groups.CAMUNDA_ADMIN).resourceType(resource).resourceId(ANY).count() == 0) {
          AuthorizationEntity userAdminAuth = new AuthorizationEntity(AUTH_TYPE_GRANT);
          userAdminAuth.setGroupId(Groups.CAMUNDA_ADMIN);
          userAdminAuth.setResource(resource);
          userAdminAuth.setResourceId(ANY);
          userAdminAuth.addPermission(ALL);
          authorizationService.saveAuthorization(userAdminAuth);
        }
      }

      identityService.createMembership("xinhua", "WEB-management");
      identityService.createMembership("xinhua", "camunda-admin");


      // authorize groups for tasklist only:


      Authorization managementTasklistAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      managementTasklistAuth.setGroupId("WEB-management");
      managementTasklistAuth.addPermission(ACCESS);
      managementTasklistAuth.setResourceId("tasklist");
      managementTasklistAuth.setResource(APPLICATION);
      authorizationService.saveAuthorization(managementTasklistAuth);

      Authorization managementReadProcessDefinition = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      managementReadProcessDefinition.setGroupId("WEB-management");
      managementReadProcessDefinition.addPermission(READ);
      managementReadProcessDefinition.addPermission(Permissions.READ_HISTORY);
      managementReadProcessDefinition.setResource(Resources.PROCESS_DEFINITION);
      authorizationService.saveAuthorization(managementReadProcessDefinition);

  
      Authorization manDemoAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      manDemoAuth.setGroupId("WEB-management");
      manDemoAuth.setResource(USER);
      manDemoAuth.setResourceId("xinhua");
      manDemoAuth.addPermission(READ);
      authorizationService.saveAuthorization(manDemoAuth);


    }

}
