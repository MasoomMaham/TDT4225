package exercise2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * These libs should be used according to the exercise text
 *
 * import java.util.*;
 * import java.nio.ByteBuffer;
 * import java.nio.channels.*;
 * import java.nio.file.*;
 * import java.io.IOException;
 * import java.nio.file.StandardOpenOption;
 * import static java.nio.file.StandardOpenOption.APPEND;
 * import static java.nio.file.StandardOpenOption.WRITE;
 * import static java.nio.file.StandardOpenOption.CREATE;
 **/

public class IOTest {

    private static final int BLOCKSIZE = 8192;
    //edit this value to change the file size
    private static final long NBLOCKS = 2*131072;

    /**
     * method to create a new file
     */
    private void createFile(){
        try{
            Path file = Paths.get(System.getProperty("user.dir"), "myjavadata");
            SeekableByteChannel out = Files.newByteChannel(file, EnumSet.of(CREATE, APPEND));

            //Start the timer
            long start = System.currentTimeMillis();
            for (int i = 0; i < NBLOCKS; i++) {
                ByteBuffer buff = ByteBuffer.allocate(BLOCKSIZE);
                out.write(buff);
            }
            //end timer and find the total time used
            long time = System.currentTimeMillis() - start;
            System.out.println("It took "+time+" ms to write this file to disk");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }


    /**
     * main method to run the createFile method
     * @param args
     */
    public static void main(String[] args) {
        IOTest test = new IOTest();
        test.createFile();
    }

}
