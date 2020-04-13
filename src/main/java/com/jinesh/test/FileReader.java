package com.jinesh.test;

import com.jinesh.test.PRG1.TabA;
import com.jinesh.test.PRG2.PRG2;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Jinesh.Sanghvi on 04/12/2020.
 */
public class FileReader {

    public static <T> List<T> readFile(URI uri, int numberOfParts, Function<String[], T> parser) throws Exception
    {
        List<T> contents = new LinkedList<>() ;
        List<String> lines = Files.readAllLines(Paths.get(uri) ) ;

        // List<TabA> tabAList = new LinkedList<>();

        String[] parts ;
        for(String line : lines){
            if(line.startsWith("#"))
                continue;
            parts = line.split("\\s*,\\s*");
            if(parts.length != numberOfParts)
                throw new Exception("Incorrect file format. expected " + numberOfParts + " but found " + parts.length + " parts") ;

            contents.add(parser.apply(parts));
        }
        return contents;
    }
}

