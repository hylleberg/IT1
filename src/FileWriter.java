/**
 *
 */
class FileWriter {

    private File logFile = new File("/log/" +"score.txt");
    private FileOutputStream oFile;
    /**
     * Responsible for writing to file.
     */
    FileWriter(){
        Files.createDirectories(Paths.get("/log"));
        logFile.createNewFile(); // if file already exists will do nothing
        FileOutputStream oFile = new FileOutputStream(logFile, false);
    }
    /**
     * Responsible for writing to file.
     */
    FileWriter(String fileName){
        Files.createDirectories(Paths.get("/log"));
        this.logFile = new File("/log/" +fileName + ".txt");
        logFile.createNewFile(); // if file already exists will do nothing
        FileOutputStream oFile = new FileOutputStream(yourFile, false);
    }

    /**
     *
     * @param temp
     */
    void writeTempertaureToFile(double temp){
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                this.oFile, "utf-8"))) {
            writer.write("The latest temperature: " + temp + " \u00B0"+"C \n");
        } catch (IOException e) {
            e.printStacjTrace();
        } finally {
            if(writer != null) {
                writer.close();
            }
        }
    }

}
