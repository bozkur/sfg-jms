package guru.springframework.sfgjms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SfgJmsApplication {

    public static void main(String[] args) {
       /* ConfigurationImpl configuration = new ConfigurationImpl()
                .setPersistenceEnabled(false)
                .setJournalDirectory("target/data/journal")
                .setSecurityEnabled(false)
                .addAcceptorConfiguration("invm", "vm://15000");
        //ActiveMQServer activeMQServer = ActiveMQServers.newActiveMQServer(configuration);
        ActiveMQServer activeMQServer = new ActiveMQServerImpl(configuration);
        activeMQServer.start();*/
        SpringApplication.run(SfgJmsApplication.class, args);
    }

}
