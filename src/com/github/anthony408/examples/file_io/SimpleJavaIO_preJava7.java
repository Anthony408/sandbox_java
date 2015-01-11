package com.github.anthony408.examples.file_io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class SimpleJavaIO_preJava7{

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

    static void readLinesUsingFileReader(File file) throws IOException
    {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        String lookFor = "xyz:";
        while((line = br.readLine()) != null){
            if(line.contains(lookFor)){
                System.out.println("Line Contained("+lookFor+"):"+line);
            }
        }
        br.close();
        fr.close();
    }
    
    /**
     * Example of the following:
     * 1. gets the current working directory of the project (base root of sandox_java)
     * 2. builds the path to a file using combinePaths method
     * @param args -- nothing with these.
     */
    public static void main (String args[]) throws IOException {

        String workingDir = System.getProperty("user.dir");
        System.out.println("Current working directory : " + workingDir);
        String[] filepaths = new String[] {workingDir, "data_files", "examples_java_io", "data.txt"};
        String filepath = combinePaths(filepaths);
        File f = new File(filepath);
        if (f.exists()){
            System.out.println(String.format("File %s Exists!", f));
        } else {
            System.out.println(String.format("File %s Does Not Exist!", f));
        }

        try {
            readLinesUsingFileReader(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
--------------------------------------------
-- OUTPUT

1.  reads input file specified @ relative path to project
2.  parses each line and looks for the "xyz:" token in a line, and prints FOUND! when it finds it.

See output below.
--------------------------------------------

File /Users/agaglian/IdeaProjects/sandbox_java/data_files/examples_java_io/data.txt Exists!
Line Contained(xyz:):xyz:  this is the line we're looking for!
--------------------------------------------
*/