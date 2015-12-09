package stackoverflow;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * @author v.chibrikov
 */
public class FolderReader {
    public static void main(String[] args) throws Exception {
        FolderReader reader = new FolderReader();
        String infoPath = "info.file";
        String info = reader.getInfoFile(infoPath)[0];

        String infoExpected = "c:\\tmp\\test";
        System.out.println(info.equals(infoExpected));

        File file = new File(infoExpected);
        System.out.println(file.getCanonicalFile());
        System.out.println(file.exists());
        System.out.println(file.isDirectory());

        reader.moviePosterFilePath(file);
        String[] names = reader.folderNames(file);
        System.out.println(names.length);
    }


    public String[] folderNames(File path) {
        return path.list();

    }

    public File[] moviePosterFilePath(File path) {
        String[] foldernames = folderNames(path);//list of all the folder names in the movies folder
        File[] PosterFilePathArray = new File[folderNames(path).length];

        for (int folder = 0; folder < folderNames(path).length; folder++) {
            File insideFolder = new File(path.toString() + "\\" + foldernames[folder]);

            for (int file = 0; file < insideFolder.list().length; file++) {
                if (insideFolder.list()[file].contains(".png") || insideFolder.list()[file].contains(".jpg") || insideFolder.list()[file].contains(".gif")) {
                    File finalPath = new File(path.toString() + "\\" + foldernames[folder] + "\\" + insideFolder.list()[file]);
                    PosterFilePathArray[folder] = finalPath;
                }
            }
        }
        return PosterFilePathArray;
    }

    public File[] VidFilePath(File path) {
        String[] foldernames = folderNames(path);
        File[] VidFilePathArray = new File[folderNames(path).length];

        for (int folder = 0; folder < folderNames(path).length; folder++) {
            File insideFolder = new File(path.toString() + "\\" + foldernames[folder]);

            for (int file = 0; file < insideFolder.list().length; file++) {
                if (insideFolder.list()[file].contains(".avi") || insideFolder.list()[file].contains(".mkv") || insideFolder.list()[file].contains(".mp4") || insideFolder.list()[file].contains(".webm") || insideFolder.list()[file].contains(".mpg") || insideFolder.list()[file].contains(".m4v")) {
                    File finalPath = new File(path.toString() + "\\" + foldernames[folder] + "\\" + insideFolder.list()[file]);
                    VidFilePathArray[folder] = finalPath;
                }
            }
        }

        return VidFilePathArray;

    }

    public String[] getInfoFile(String file) {
        String[] infoFileInfo = {"", ""};
        File source = new File(file);
        try {
            if (source.exists()) {
                System.out.println("I found your file");
                BufferedReader br = null;


                br = new BufferedReader(new FileReader(source));
                for (int i = 0; i < infoFileInfo.length; i++) {
                    infoFileInfo[i] = br.readLine();
                }

                br.close();
            } else {
                System.out.println("Yea, Your folder Dosent Exist");

                if (source.createNewFile())
                    System.out.println("File is created!");

                else
                    System.out.println("File already exists.");

                JOptionPane.showMessageDialog(null, "OOPS! It looks like this program isnt paired with a source folder. Please select the folder containing all of your media files.");
                setInfo(file);
                getInfoFile(file);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        System.out.println(infoFileInfo[0].toString());
        return infoFileInfo;


    }

    private void setInfo(String file) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(chooser);

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            writer.println(chooser.getSelectedFile().getAbsolutePath());

            System.out.println("You chose to open this file: "
                    + chooser.getSelectedFile().getAbsolutePath());

            Object[] options = {"Movies",
                    "TV Shows", "Huh?"};
            while (true) {
                int n = JOptionPane.showOptionDialog(null,
                        "In what format is the folder you provided",
                        "Folder Format",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[2]);
                if (n == 0)//chose movies
                {
                    writer.println("0");
                    break;
                } else if (n == 1)//chose tv shows
                {
                    writer.println("1");
                    break;
                } else//chose huh?
                {
                    try {
                        Desktop.getDesktop().open(new File("help.html"));
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            writer.close();
        }
    }

}
