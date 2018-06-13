
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;


import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Catalog {
        public static void main(String[] args) throws IOException {
            ArrayList<File> directory = new ArrayList<>();
            ArrayList<File> mp3File = new ArrayList<>();
            ArrayList<String[]> tags = new ArrayList<>();
            if (args.length != 0) {
                for (int i = 0; i < args.length; i++) {
                    directory.add(new File(args[i]));
                }

                for (int i = 0; i < args.length; i++) {
                    mp3File.addAll(mp3Files(directory.get(i)));
                }

                tags.addAll(readTags(mp3File));

                for (int i = 0; i < tags.size(); i++) {
                    for (int j = 0; j < 5; j++) {
                        System.out.print(tags.get(i)[j]);
                    }
                    createHTML(tags);
                }
            }else
                System.out.println("??????? ???????? ????????? ????? ? ???????");
        }

        static ArrayList<File> mp3Files(File file) throws IOException {
            ArrayList<File> files = new ArrayList<>();
            ArrayList<File> mp3 = new ArrayList<>();
            for (int i = 0; i < file.listFiles().length; i++) {
                files.add(file.listFiles()[i]);
            }

            for (int i = 0; i < files.size(); i++) {
                if (files.get(i).isFile() & files.get(i).getAbsolutePath().endsWith(".mp3")) {
                    mp3.add(files.get(i));
                } else if (files.get(i).isDirectory()) {
                    mp3.addAll(mp3Files(files.get(i)));
                }
            }
            return mp3;
        }

        static ArrayList<String[]> readTags(ArrayList<File> mp3FILES) {
            ArrayList<String[]> songsTags = new ArrayList<>();
                try {
                    for (int i = 0; i < mp3FILES.size(); i++) {
                        System.out.println(mp3FILES.get(i).getName());
                        Mp3File mp3 = new Mp3File(mp3FILES.get(i).getAbsolutePath());
                        String mass[] = new String[5];

                        mass[0] = mp3.getId3v1Tag().getArtist();
                        mass[1] = mp3.getId3v1Tag().getAlbum();
                        mass[2] = mp3.getId3v1Tag().getTitle();
                        mass[3] = String.valueOf((mp3.getLengthInSeconds()));
                        mass[4] = mp3FILES.get(i).getAbsolutePath();
                        songsTags.add(mass);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                }
            return songsTags;
        }

        static void createHTML(ArrayList<String []> lisOfTags)
        {
            HashSet<String> dublWays = new HashSet<>();
            HashSet<String[]> Artist  = new HashSet<>();
            ArrayList<String[]> list = new ArrayList<>();
            ArrayList<String> dubl = new ArrayList<>();// ????????? ??? ?????? ? HTML
            for (int j = 0 ; j < lisOfTags.size();j++)
            {
                String mass [] = new String[2];
                mass[0] = lisOfTags.get(j)[0];
                mass[1] = lisOfTags.get(j)[1];
                Artist.add(mass);
            }
            for (int i = 0; i < lisOfTags.size()-1;i++)
                for (int z = i+1; z < lisOfTags.size();z++ )
                {
                    if(lisOfTags.get(i)[0].equals(lisOfTags.get(z)[0])&lisOfTags.get(i)[1].equals(lisOfTags.get(z)[1])
                    & lisOfTags.get(i)[2].equals(lisOfTags.get(z)[2]))
                    {
                        dublWays.add(lisOfTags.get(i)[4]);
                        dublWays.add(lisOfTags.get(z)[4]);
                    }
                }
            dubl.addAll(dublWays);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("./SONGS.html"));)
            {
                writer.write("<html>");
                writer.write("<body>");
                for (int i = 0 ; i < lisOfTags.size();i++)
                {
                    writer.write("<h1>" + lisOfTags.get(i)[0] + "</h1>");
                    writer.write("<p>" +  lisOfTags.get(i)[1] + "</p>");
                    writer.write("<p>" +  lisOfTags.get(i)[2] + " " + lisOfTags.get(i)[3] + "  " + "</p>");
                    writer.write("<a href=\"" + lisOfTags.get(i)[4] + "\">Link</a>");
                }
                if (!dubl.isEmpty())
                {writer.write("<h1>"+" Dublicates "+ "</h1>");
                    for(int i =0; i< dubl.size();i++) {
                        writer.write("<p>" + dubl.get(i) + "</p>");
                    }
                }
                writer.write("</body>");
                writer.write("</html>");

            }catch (IOException exc )
            {
                exc.getStackTrace();
            }
        }
    }


