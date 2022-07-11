package org.example.question4;

import java.io.IOException;

public interface ParentCommand {

    public void work(String root, String[] parses, String filename) throws IllegalAccessException, IOException;

    public boolean support(String root, String[] parse);
}
