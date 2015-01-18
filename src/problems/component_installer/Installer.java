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


/*
-- OUTPUT -- 
/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/bin/java -Didea.launcher.port=7536 "-Didea.launcher.bin.path=/Applications/IntelliJ IDEA 14.app/Contents/bin" -Dfile.encoding=UTF-8 -classpath "/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/lib/ant-javafx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/lib/javafx-mx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/lib/tools.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/deploy.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/javaws.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/jfxswt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/plugin.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/ext/cldrdata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/ext/jfxrt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/ext/nashorn.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/Users/agaglian/IdeaProjects/sandbox_java/out/production/sandbox_java:/Users/agaglian/.m2/repository/org/abego/treelayout/org.abego.treelayout.core/1.0.1/org.abego.treelayout.core-1.0.1.jar:/Users/agaglian/.m2/repository/org/abego/treelayout/org.abego.treelayout.demo/1.0.1/org.abego.treelayout.demo-1.0.1.jar:/Users/agaglian/.m2/repository/jgraph/jgraph/5.13.0.0/jgraph-5.13.0.0.jar:/Applications/IntelliJ IDEA 14.app/Contents/lib/idea_rt.jar" com.intellij.rt.execution.application.AppMain problems.component_installer.Installer
DEPEND TELNET TCPIP NETCARD
DBG NODE:
--------------------------------
Name: TELNET
ExplicitlyInstalled: false
Parents: []
Children: [TCPIP, NETCARD]
--------------------------------

DEPEND TCPIP NETCARD
DBG NODE:
--------------------------------
Name: TCPIP
ExplicitlyInstalled: false
Parents: [TELNET]
Children: [NETCARD]
--------------------------------

DEPEND DNS TCPIP NETCARD
DBG NODE:
--------------------------------
Name: DNS
ExplicitlyInstalled: false
Parents: []
Children: [TCPIP, NETCARD]
--------------------------------

DEPEND BROWSER TCPIP HTML
DBG NODE:
--------------------------------
Name: BROWSER
ExplicitlyInstalled: false
Parents: []
Children: [TCPIP, HTML]
--------------------------------

INSTALL NETCARD
	Installing NETCARD
INSTALL TELNET
	Installing TCPIP
	Installing TELNET
LIST
	NETCARD
	TCPIP
	TELNET
INSTALL foo
DBG NODE:
--------------------------------
Name: foo
ExplicitlyInstalled: false
Parents: []
Children: []
--------------------------------

	Installing foo
REMOVE NETCARD
	NETCARD is still needed.
INSTALL BROWSER
	Installing HTML
	Installing BROWSER
INSTALL DNS
	Installing DNS
LIST
	NETCARD
	TCPIP
	TELNET
	foo
	HTML
	BROWSER
	DNS
REMOVE TELNET
	Removing TELNET.
	...Cant remove(TCPIP)! still has parents([BROWSER])
	...Cant remove(NETCARD)! still has parents([DNS, TCPIP])
REMOVE NETCARD
	NETCARD is still needed.
REMOVE DNS
	Removing DNS.
	...Cant remove(TCPIP)! still has parents([BROWSER])
	...Cant remove(NETCARD)! still has parents([TCPIP])
REMOVE NETCARD
	NETCARD is still needed.
INSTALL NETCARD
	NETCARD is already installed.
REMOVE TCPIP
	TCPIP is still needed.
LIST
	NETCARD
	TCPIP
	foo
	HTML
	BROWSER
REMOVE BROWSER
	Removing BROWSER.
	Removing TCPIP.
	Removing HTML.
REMOVE TCPIP
	Removing TCPIP.
LIST
	NETCARD
	foo
END

 */