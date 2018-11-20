package com.lvyj001.listfile;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ListProjectFile {

    /**
     * 根据src/main/resources/project 目录下所有的项目属性文件中的项目存放路径
     * 获取该项目中所有的java文件清单，并将清单文件保存至 lists/project 文件中
     *
     * @return List<String> 返回每个项目的清单文件的路径 e.g { ...lists/project1,...list/project2 ,...}
     */
    public static List<String> initFileList() throws IOException {
        // 返回所有清单文件的路径
        List<String> lists = new ArrayList<>();

        String path = ListProjectFile.class.getClassLoader().getResource("projects").getPath();

        File projectDir = new File(path);
        // 用于存放项目清单文件
        Path listDir = Paths.get(projectDir.getParent(), "lists/");
        Files.createDirectories(listDir);

        File[] projectPathes = projectDir.listFiles();
        for (File projectPath : projectPathes) {
            // 创建项目中java文件的清单 project
            // 根据properties文件获取项目路径
            Properties properties = new Properties();
            try (FileInputStream fis = new FileInputStream(projectPath)) {
                properties.load(fis);
            }
            Path project = Paths.get(properties.getProperty("baseDir"));
            if (!project.toFile().exists()) {
                return null;
            }

            // 遍历项目目录，获取java文件清单
            String projectName = properties.getProperty("project");
            Path projectFileList = Paths.get(listDir.toString(), projectName);
            // 如果文件已存在，则不会创建新的文件而且抛出异常
            Files.deleteIfExists(projectFileList);
            // 创建新的Java文件清单
            Files.createFile(projectFileList);
            BufferedWriter bw = Files.newBufferedWriter(projectFileList, Charset.forName("utf-8"), StandardOpenOption.APPEND);
            Files.walk(project).filter(new Predicate<Path>() {
                @Override
                public boolean test(Path path) {
                    if (path.toString().endsWith(".java") && !path.toString().contains("\\target\\classes")) {
                        return true;
                    }
                    return false;
                }
            }).forEach(new Consumer<Path>() {
                @Override
                public void accept(Path path) {
                    try {
                        bw.write(path.toString());
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            bw.flush();
            bw.close();
            lists.add(projectFileList.toString());
        }

        return lists;
    }
}
