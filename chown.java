public class chown {
    /**
     * The name of this program.
     * This is the program name that is used
     * when displaying error messages.
     */
    public static String PROGRAM_NAME = "chown";


    public static void main(String[] args) throws Exception
    {
        // initialize the file system simulator kernel
        Kernel.initialize();

        int argsLenght = args.length;

        // print a useful message if not all command line arguments are specified
        if (argsLenght < 2) {
            System.err.println( PROGRAM_NAME + ": too few arguments" );
            Kernel.exit(1);
        }

        short uid = Short.valueOf(args[argsLenght -1]);

        //go through the names of all the files in which you want to change the mode
        for (int i = 0; i < argsLenght -1; i++) {

            // stat the name to get information about the file or directory
            Stat stat = new Stat() ;
            if( Kernel.stat( args[i] , stat ) < 0){
                Kernel.perror( PROGRAM_NAME ) ;
                Kernel.exit( 1 ) ;
            }

            //execution change mode program and check the results of its operation
            if (Kernel.chown(args[i], uid, false) < 0) {
                Kernel.perror(PROGRAM_NAME);
                System.err.println(PROGRAM_NAME + ": uid cannot be changed");
                Kernel.exit(3);
            }
        }

        Kernel.exit(0);
    }
}