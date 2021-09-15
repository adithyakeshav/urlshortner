package com.keshava.shorten;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.PrintWriter;

public class Arguments {
    private Options options;
    private static final String ARG_CONF = "config";
    private final File configFile;

    public Arguments(String[] args) {
        buildOptions();
        if (args.length == 0) {
            printUsage();
            throw new RuntimeException("No arguments given");
        }
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(options, args);
            if (line.getArgList().size() != 0) {
                printUsage();
                throw new RuntimeException("Arguments passed Wrong");
            }
            configFile = new File(line.getOptionValue(ARG_CONF));
            validateArgs();
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void validateArgs() {
        if (!configFile.exists()) {
            throw new RuntimeException(String.format("File '%s' does not exist", configFile.getPath()));
        }
        if (!configFile.isFile()) {
            throw new RuntimeException(String.format("Given path '%s' is not a file", configFile.getPath()));
        }
        if (!configFile.canRead()) {
            throw new RuntimeException(String.format("Insufficient privileges : Cannon read file '%s'", configFile.getPath()));
        }
    }

    private void buildOptions() {
        options = new Options();
        Option confOption = Option.builder("c")
                .longOpt(ARG_CONF)
                .argName(ARG_CONF)
                .hasArg(true)
                .required(true)
                .desc("File path to User config file")
                .build();
        options.addOption(confOption);
    }

    public File getConfigFile() {
        return configFile;
    }

    private void printUsage() {
        HelpFormatter formatter = new HelpFormatter();
        PrintWriter writer = new PrintWriter(System.out);
        formatter.printUsage(writer, 100, "demoApp [options]");
        formatter.printOptions(writer, 100, options, 3, 3);
        writer.close();
    }
}
