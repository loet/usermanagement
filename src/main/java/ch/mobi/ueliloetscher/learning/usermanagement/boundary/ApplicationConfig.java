/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.mobi.ueliloetscher.learning.usermanagement.boundary;

import ch.mobi.ueliloetscher.learning.usermanagement.util.ClientRequestFilter;

import javax.ws.rs.core.Application;
import java.util.Set;

@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(UserManagementRestResource.class);
        resources.add(EmployeeManagementBean.class);
        resources.add(ClientRequestFilter.class);
    }

}
