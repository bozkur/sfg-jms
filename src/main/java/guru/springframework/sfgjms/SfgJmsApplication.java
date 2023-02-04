package guru.springframework.sfgjms;

import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.core.server.impl.ActiveMQServerImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SfgJmsApplication {

    public static void main(String[] args) throws Exception {
        ConfigurationImpl configuration = new ConfigurationImpl()
                .setPersistenceEnabled(false)
                .setJournalDirectory("target/data/journal")
                .setSecurityEnabled(false)
                .addAcceptorConfiguration("invm", "vm://15000");
        //ActiveMQServer activeMQServer = ActiveMQServers.newActiveMQServer(configuration);
        ActiveMQServer activeMQServer = new ActiveMQServerImpl(configuration);
        activeMQServer.start();
        SpringApplication.run(SfgJmsApplication.class, args);
    }

}
