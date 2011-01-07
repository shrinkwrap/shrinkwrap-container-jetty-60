/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.shrinkwrap.dependencies;

import java.io.File;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.dependencies.impl.MavenDependencies;
import org.junit.Test;

/**
 * @author <a href="mailto:kpiwko@redhat.com">Karel Piwko</a>
 * 
 */
public class ArtifactDependenciesUnitTestCase
{

   /**
    * Tests a resolution of an artifact from central with custom settings
    * @throws DependencyException
    */
   @Test
   public void testPomBasedArtifact() throws DependencyException
   {
      String name = "pomBasedArtifact";

      WebArchive war = ShrinkWrap.create(WebArchive.class, name + ".war")
            .addLibraries(Dependencies.use(MavenDependencies.class)
                                      .configureFrom("src/test/resources/pom-dep/settings.xml")
                                      .artifact("org.jboss.shrinkwrap:shrinkwrap-dependencies-pom-dep:pom:1.0.0")
                                      .resolve());

      DependencyTreeDescription desc = new DependencyTreeDescription(new File("src/test/resources/dependency-trees/" + name + ".tree"));
      desc.validateArchive(war).results();

      war.as(ZipExporter.class).exportTo(new File("target/" + name + ".war"), true);
   }

   
}
