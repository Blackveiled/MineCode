/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackveiled.minecode.compiler;

/**
 *
 * @author Blackveiled
 */
public class MineCodeCompiler {

    /**
     * This constructor will compile scripts into files that can be read by the
     * MineCodeReader. Only use this constructor for UTF-16 encoded characters.
     * If you are not using UTF-16 characters with this constructor the compiler
     * cannot perform properly.
     *
     * @param in UTF-16 encoded char array
     */
    public MineCodeCompiler(char[] in) {

        // MineCode Information:
        // 
        // - Whitespace will be ignored by the compiler, the only instance where whitespace
        // will not be ignored is if a space is contained between '' or "".
        //
        ///////////////////////////////////////////////////////////////////////////////////////

        char unknown = '\u0000';
        char space = '\u0020';
        char comment = '\u002f';

        //  Byte Conversion Values
        //
        // The following values are to be the output of the script being compiled.
        // These values are saved and when ran, allow the script to be read from
        // by the MinecodeReader.  When read by the reader, specific actions will
        //
        // 0x00 - Open string
        // 0x01 - Close string
        // 0x0a - Open char
        // 0x0b - Close char
        byte[] c = new byte[6];
        c[0] = 0x00; // Open String
        c[1] = 0x01; // Close String
        c[2] = 0x02; // if
        c[3] = 0x03; // =    | equals
        c[4] = 0x04; // >=  |equal to or greater than
        c[5] = 0x05; // <=  | less than or equal to

        // Booleans
        //
        // These booleans are "switches" to convert the approapriate data.
        //
        // noErrorsFound - If an error has been found, the compiler will immediately halt
        // the conversion process.  As a result, there will be no file saved or altered.
        // The error code will result in '0'.  
        boolean noErrorsFound = true;
        boolean scriptOpen = false;
        boolean functionOpen = false;
        boolean setVariableName = false;
        boolean commentOpen = false;
        boolean stringOpen = false;

        //  Minecode Error Codes
        //
        // CODE     |               CODE DESCRIPTION
        //  0           |   The compiler failed to finish executing the script.
        //  1           |   The code contained errors and compiler stopped execution.
        //  2           |   The code was successfully compiled.
        byte error = 0;
        int i = 0;
        byte[] output = new byte[in.length];
        noErrorsFound:
        while (i < in.length) {
            if (!noErrorsFound) {
                break noErrorsFound;
            }
            // If a string is open, the compiler will ignore every character, except quotation marks.
            if (stringOpen) {
                for (int e = i; e < in.length; e++) {
                    if (in[e] == '\u0022') {
                        stringOpen = false;
                        break;
                    }

                }
            } else {
                if (in[i] != '\u002f' ^ in[i + 1] != '\u002f') {

                    if (in[i] == 'n' ^ in[i + 1] == 'e' ^ in[i + 2] == 'w') {

                    }
                }
            }
        }

        switch (error) {
            case 0:
                System.out.print("The compiler failed to finish executing the code.");
                break;
            case 1:
                System.out.print("The code contained errors, please fix your errors.");
                break;
            case 2:
                System.out.print("The code was successfully compiled");
        }
    }
}
