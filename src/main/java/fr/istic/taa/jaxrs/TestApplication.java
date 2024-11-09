/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.istic.taa.jaxrs;

import java.util.HashSet;
import java.util.Set;


import javax.ws.rs.core.Application;

import fr.istic.taa.jaxrs.rest.BugRessource;
import fr.istic.taa.jaxrs.rest.CommentaireRessource;
import fr.istic.taa.jaxrs.rest.CorsFilter;
import fr.istic.taa.jaxrs.rest.LoginResource;
import fr.istic.taa.jaxrs.rest.PetResource;
import fr.istic.taa.jaxrs.rest.TagRessource;
import fr.istic.taa.jaxrs.rest.TicketRessource;
import fr.istic.taa.jaxrs.rest.UserService;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

public class TestApplication extends Application {
	

    @Override
   
    public Set<Class<?>> getClasses() {

        final Set<Class<?>> clazzes = new HashSet<Class<?>>();
        clazzes.add(CorsFilter.class);
       // clazzes.add(JwtFilter.class);
        clazzes.add(LoginResource.class);
        clazzes.add(PetResource.class);
        clazzes.add(UserService.class);
        clazzes.add(TicketRessource.class);
        clazzes.add(TagRessource.class);
        clazzes.add(BugRessource.class);
        clazzes.add(CommentaireRessource.class);
        clazzes.add(OpenApiResource.class);
        

        return clazzes;
    }

}
