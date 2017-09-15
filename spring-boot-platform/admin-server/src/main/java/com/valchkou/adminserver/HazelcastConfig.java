package com.valchkou.adminserver;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.ListConfig;
import com.hazelcast.config.MapConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
@ConditionalOnProperty(prefix = "spring.boot.admin.hazelcast", name = "members")
@ConfigurationProperties("spring.boot.admin.hazelcast")
public class HazelcastConfig extends Config {

    protected String members;
    protected String applicationStore;
    protected String eventStore;

    @PostConstruct
    public void init() {
        setProperty("hazelcast.jmx", "true");
        addMapConfig(new MapConfig(applicationStore).setBackupCount(1).setEvictionPolicy(EvictionPolicy.NONE));
        addListConfig(new ListConfig(eventStore).setBackupCount(1).setMaxSize(1000));
        String[] hosts = members.split(",");

        for (String h: hosts) {
            getNetworkConfig().getJoin().getTcpIpConfig().addMember(h).setEnabled(true);
        }
        getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        getNetworkConfig().getJoin().getAwsConfig().setEnabled(false);
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getApplicationStore() {
        return applicationStore;
    }

    public void setApplicationStore(String applicationStore) {
        this.applicationStore = applicationStore;
    }

    public String getEventStore() {
        return eventStore;
    }

    public void setEventStore(String eventStore) {
        this.eventStore = eventStore;
    }

}
