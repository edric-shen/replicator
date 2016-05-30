package com.booking.replication;

import com.booking.replication.util.CMD;
import com.booking.replication.util.StartupParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import joptsimple.OptionSet;
import org.apache.commons.cli.MissingArgumentException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws MissingArgumentException {

        OptionSet optionSet = CMD.parseArgs(args);

        StartupParameters startupParameters = new StartupParameters();
        startupParameters.init(optionSet);

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        String  configPath = startupParameters.getConfigPath();

        Configuration configuration;
        try {
            InputStream in = Files.newInputStream(Paths.get(configPath));
            configuration = mapper.readValue(in, Configuration.class);
            configuration.loadStartupParameters(startupParameters);

            if (configuration == null) {
                throw new RuntimeException(String.format("Unable to load configuration from file: %s", configPath));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("loaded configuration: " + configuration.toString());

        Replicator replicator;

        try {
            replicator = new Replicator(configuration);
            replicator.start();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
