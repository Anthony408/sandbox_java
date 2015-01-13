package problems.component_installer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.OFF;

public class Installer {

    // Logger
    private final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

    private ComponentTree componentTree = new ComponentTree();
    
    public Installer(){
        LOGGER.setLevel(OFF);
    }
    
    /**
     * Utility method to combine paths (pre jdk 7, not using the Path object)
     * @param paths (array of stings that will make up a path)
     * @return string representation of the paths combined.
     */
    public static String combinePaths(String ... paths)
    {
        if ( paths.length == 0)
        {
            return "";
        }
        File combined = new File(paths[0]);
        int i = 1;
        while ( i < paths.length)
        {
            combined = new File(combined, paths[i]);
            ++i;
        }
        return combined.getPath();
    }

    // valid commands
    public enum COMMANDS {DEPEND, INSTALL, REMOVE, LIST, END};

    private void run_installer(File inputFile) {
        LOGGER.info(String.format("Running Installer on file: %s", inputFile.getAbsolutePath()));
//        LOGGER.setLevel(Level.OFF);
        ComponentTree componentTree = new ComponentTree();
        
        String line;
        String command_str; // current command parsed from the line.

        
        // Read line by line of the input file

        try {
            FileReader fr = new FileReader(inputFile.getAbsoluteFile());
            BufferedReader br = new BufferedReader(fr);

            while((line = br.readLine()) != null){
                // parse line into tokens.
                String regex_whitespace = "\\s+";

                ArrayList<String> line_tokenized_array;
                line_tokenized_array = new ArrayList(Arrays.asList(line.split(regex_whitespace)));
                command_str = line_tokenized_array.get(0); // first element of the line_tokenized_list is the command.
                List<String> args;
                if (line_tokenized_array.size() > 1) {
                    args = line_tokenized_array.subList(1, line_tokenized_array.size()); // rest of the elements are the args.
                } else {
                    args = null;
                }


                COMMANDS command = COMMANDS.valueOf(command_str);
                String name;
                System.out.println(line);
                switch (command) {
                    case DEPEND:
                        LOGGER.info(String.format("RUNNER FOUND %s CASE", command));
                        // get name
                        assert args != null;
                        name = args.get(0);
                        List<String> dependencies = args.subList(1, args.size() );
                        handleDepend(name, dependencies);
                        break;
                    case INSTALL:
                        LOGGER.info(String.format("RUNNER FOUND %s CASE", command));
                        // get name
                        assert args != null;
                        name = args.get(0);
                        handleInstall(name);
                        break;
                    case REMOVE:
                        LOGGER.info(String.format("RUNNER FOUND %s CASE", command));
                        assert args != null;
                        name = args.get(0);
                        handleRemove(name);
                        break;
                    case LIST:
                        LOGGER.info(String.format("RUNNER FOUND %s CASE", command));
                        handleList();
                        break;
                    case END:
                        LOGGER.info(String.format("RUNNER FOUND %s CASE", command));
                        handleEnd();
                        System.exit(0);
                        break;
                    default:
                        LOGGER.info(String.format(" ... default?  Should not have reached here ....@%s CASE", command));
                        break;
                }
            }
            // close the input file
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles "DEPEND" case.
     */
    private void handleDepend(String name, List<String> children) {
        componentTree.executeDepend(name, children);
    }

    /**
     * handles "INSTALL" case.
     */
    private void handleInstall(String name) {
        componentTree.executeInstall(name);
    }

    /**
     * handles "REMOVE" case.
     */
    private void handleRemove(String name) {
        componentTree.executeRemove(name);
    }

    
    /**
     * handles "LIST" case.
     */
    private void handleList() {
        componentTree.executeList();
    }

    /**
     * handles "END" case.
     */
    private void handleEnd() {
        System.exit(0);
    }

    /*
     * Entry point, calls the installer.
     */
    public static void main(String[] args) {

        Installer installer = new Installer();
//        String filename = "input_test_remove.dat";
//        String filename = "input_test_multi_level_install.dat";
        String filename = "input_1.dat";
        Path inputFilePath = Paths.get("data_files", "component_installer", filename);
        // create File object based on relative input file paths.
        File inputFile = inputFilePath.toFile();
        // Call the installer on the input file.
        installer.run_installer(inputFile);
    }
}
