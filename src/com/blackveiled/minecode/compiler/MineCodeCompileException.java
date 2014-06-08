package com.blackveiled.minecode.compiler;

/**
 *
 * @author Blackveiled
 */
public class MineCodeCompileException extends Exception {
    
    final byte i;
    
    public MineCodeCompileException(byte i) {
        super();
        this.i = i;
    }
    
    @Override
    public String getLocalizedMessage() {
        switch (i) {
            case 0:
                System.out.print("The compiler failed to finish executing the code.");
                break;
            case 1:
                System.out.print("The code contained errors, please fix your errors.");
                break;
            case 2:
                System.out.print("The code was successfully compiled");
                break;
            case 4:
                System.out.print("Illegal characters found during execution, compile failed.");
                break;
            case 5:
                System.out.print("End of file reached. String quotation marks have not been closed.");
        }
        return "An unknown error-code has been thrown.!";
    }
}
