import java.util.*;
import java.io.*;
public class PA5{
        private int found = 0;
        private long foundCount = 0L;
        private int notFound = 0;
        private long notCount = 0L;
        private BinarySearchTree [] list = new BinarySearchTree[26]; 

        
        /*
         *Pre: none
         *
         *Post: puts the dictionary into the list where each letter has its own list
         *
         */
        public void laod(){
                for(int i  =  0  ;  i<   list.length; i++){
                        list[i]  =  new BinarySearchTree<String>();
                }
                try{
                        File f = new File("random_dictionary.txt");
                        Scanner input = new Scanner(f);
                        while(input.hasNext()){
                                String newWord = input.nextLine();
                                newWord = newWord.toLowerCase();
                                list[newWord.charAt(0) - 97].insert(newWord);
                        }
                       input.close(); 
                } 
                catch(Exception e){
                        System.out.println("DFile did not work" + e);
                }
        }


        /*
         *Pre: laod needs to have been run
         *
         *Post: changes the four variables
         *      found and notFound are set to the number of words that are or are not in the dictionary
         *      foundCount and notCount are set to the number of comparisons that are use to see if words are the dictionary
         *
         */
        public void find(){
                try{
                        File f = new File("oliver.txt");
                        Scanner input = new Scanner(f);
                        while(input.hasNext()){
                                String newLine = input.nextLine();
                                while(newLine.compareTo("") == 0){
                                        newLine = input.nextLine();
                                }
                                newLine = newLine.toLowerCase();
                                for(int i = 0; i < newLine.length(); i++){
                                        if(!((newLine.charAt(i) > 96 && newLine.charAt(i) < 123) || newLine.charAt(i) == 32 || newLine.charAt(i) == 39)){
                                                if(!(i == newLine.length() -1)){
                                                        newLine = newLine.substring(0, i) + newLine.substring(i+1);
                                                }
                                                else{
                                                        newLine = newLine.substring(0,i);
                                                }
                                                i--;
                                        }
                                }
                                if(newLine.isEmpty() || newLine.trim().isEmpty() || newLine.trim().compareTo("'") == 0){
                                        continue;
                                }
                                while(newLine.charAt(0) == 32 || newLine.charAt(0) == 39){
                                        newLine = newLine.substring(1);
                                }
                                String[] lineArr = newLine.split(" ");
                                for(int i = 0; i < lineArr.length; i++){
                                        while(lineArr[i].compareTo("") == 0 || lineArr[i].compareTo("'") == 0){
                                                i++;
                                                if(!(i < lineArr.length)){
                                                        break;
                                                }
                                        }
                                        if(!(i < lineArr.length)){
                                                continue;
                                        }
                                        while(lineArr[i].charAt(0) == 32 || lineArr[i].charAt(0) == 39){
                                                lineArr[i] = lineArr[i].substring(1);
                                                if(!(i < lineArr.length)){
                                                        break;
                                                }

                                        }
                                        if(!(i < lineArr.length)){
                                                continue;
                                        }
                                        long[] count = new long[1];
                                        if(list[lineArr[i].charAt(0) - 97].search(lineArr[i], count) ){
                                                found++;
                                                foundCount += count[0];
                                        }
                                        else{
                                                System.out.println(lineArr[i]);
                                                notFound++;
                                                notCount += count[0];
                                        }
                                        
                                }
                        }
                        input.close();
                }
                catch(Exception e){
                        System.out.println("Oliver did not work" + e);
                }
        }

        public static void main(String[] args){
                PA5 a = new PA5();
                a.laod();
                a.find();
                System.out.println(a.found + " The Avg of the word found is " + (double)a.foundCount/a.found);
                System.out.println(a.notFound + " The Avg of the word not found is " + (double)a.notCount/a.notFound);
        }
}
