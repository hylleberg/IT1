import java.util.*;

public class FindExtensionDirs {
    
    public static void main(String[] args) {
        String extensionDirs = System.getProperty("java.ext.dirs");			// hent liste med stioplysninger
        String seperator = System.getProperty("path.separator");			// hent oplysning om stiadskildelese
        StringTokenizer t = new StringTokenizer(extensionDirs,seperator);	// adskild i enkeltstier
        System.out.println();
        System.out.println("Udvidelsesbiblioteker:");						// udskriv overskrift
        System.out.println();
        while ( t.hasMoreTokens() )											// for hver sti
            System.out.println("\t"+t.nextToken());							// udskriv sti
        System.out.println();
    }
}
