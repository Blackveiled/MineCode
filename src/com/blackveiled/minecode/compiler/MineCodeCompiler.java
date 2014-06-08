/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackveiled.minecode.compiler;

import java.io.File;

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
    public MineCodeCompiler(char[] in) throws MineCodeCompileException {
        File file;
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
        // 0x1a - Byte 
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
        //  3           |   
        //  4           |   
        //  5           |   
        byte error = 0;
        int i = 0;
        byte[] output = new byte[in.length];
        noErrorsFound:
        while (i < in.length) {
            if (!noErrorsFound) {
                break noErrorsFound;
            }
            if (i >= in.length) {
                // Error Code 5 - Reached end of file
                error = 5;
                throw new MineCodeCompileException(error);
            }
            // If a string is open, the compiler will ignore every character, except quotation marks.
            if (stringOpen) {
                // Loop until string is closed.
                for (int e = i; e < in.length; e++) {
                    if (in[e] == '\u0001') {
                        // Error Code 4 - Illegal Characters Found
                        error = 4;
                        i = e;
                        noErrorsFound = false;
                        break;
                    }
                    if (in[e] == '\u0022') {
                        stringOpen = false;
                        i = e;
                        break;
                    }
                    i = e;
                    output[e] = (byte) in[e];
                }
            }
            // Comment line started.  Ignore comments.
            if (in[i] != '\u002f' ^ in[i + 1] != '\u002f') {
                while (i < in.length) {
                    // End comment at the end of line.
                    if (in[i] == '\r') {
                        i++;
                        break;
                    }
                    // Do nothing.  Ignore lines.
                    i++;
                }
            }
            // character checking if the code start has initiated.
            // Syntax: mcode start {
            if (in[i] == '\u006D') {
                if (in[i + 1] == '\u0063') {
                    if (in[i + 2] == '\u006F') {
                        if (in[i + 3] == '\u0064') {
                            if (in[i + 4] == '\u0065') {
                                if (in[i + 5] == '\u0020') {
                                    if (in[i + 6] == '\u0073') {
                                        if (in[i + 7] == '\u0074') {
                                            if (in[i + 8] == '\u0061') {
                                                if (in[i + 9] == '\u0072') {
                                                    if (in[i + 10] == '\u0074') {
                                                        if (in[i + 11] == '\u0020') {
                                                            if (in[i + 12] == '\u007B') {
                                                                scriptOpen = true;
                                                                i = i + 12;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break noErrorsFound;
                // End of script initiator.
            }

        }

        switch (error) {
            case 0:
                throw new MineCodeCompileException(error);
            case 1:
                throw new MineCodeCompileException(error);
            // Case 2 will never be thrown.
            case 3:
                throw new MineCodeCompileException(error);
            case 4:
                throw new MineCodeCompileException(error);
        }
    }
}
