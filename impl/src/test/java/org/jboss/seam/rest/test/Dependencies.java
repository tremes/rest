package org.jboss.seam.rest.test;

import java.io.InputStream;

import org.jboss.osgi.testing.ManifestBuilder;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.asset.Asset;
import org.jboss.shrinkwrap.api.container.ManifestContainer;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;

public class Dependencies {
    public static final Archive<?>[] SEAM_SOLDER = DependencyResolvers.use(MavenDependencyResolver.class)
            .loadReposFromPom("pom.xml").artifact("org.jboss.seam.solder:seam-solder").resolveAs(GenericArchive.class)
            .toArray(new Archive<?>[0]);

    public static final Archive<?>[] SEAM_CATCH = DependencyResolvers.use(MavenDependencyResolver.class)
            .loadReposFromPom("pom.xml").artifact("org.jboss.seam.catch:seam-catch").resolveAs(GenericArchive.class)
            .toArray(new Archive<?>[0]);

    public static final Archive<?>[] FREEMARKER = DependencyResolvers.use(MavenDependencyResolver.class)
            .loadReposFromPom("pom.xml").artifact("org.freemarker:freemarker").exclusion("*").resolveAs(GenericArchive.class)
            .toArray(new Archive<?>[0]);

    public static final Archive<?>[] VELOCITY = DependencyResolvers.use(MavenDependencyResolver.class)
            .loadReposFromPom("pom.xml").artifact("org.apache.velocity:velocity").resolveAs(GenericArchive.class)
            .toArray(new Archive<?>[0]);

    public static final Archive<?>[] VELOCITY_TOOLS = DependencyResolvers.use(MavenDependencyResolver.class)
            .loadReposFromPom("pom.xml").artifact("org.apache.velocity:velocity-tools").exclusion("*")
            .resolveAs(GenericArchive.class).toArray(new Archive<?>[0]);
    
    public static <T extends ManifestContainer<?>> T addDependencyToManifest(T archive, final String dependencies)
    {
        archive.setManifest(new Asset() {

            @Override
            public InputStream openStream() {
                return ManifestBuilder.newInstance().addManifestHeader("Dependencies", dependencies)
                        .openStream();
            }
        });
        return archive;
    }
    
    public static <T extends ManifestContainer<?>> T addJBossLoggingDependencyToManifest(T archive)
    {
        return addDependencyToManifest(archive, "org.jboss.logging,org.jboss.logmanager");
    }
}
