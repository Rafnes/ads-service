package ru.skypro.homework.exception;

import java.io.IOException;

public class FailedToReadFileException extends RuntimeException{
   FailedToReadFileException(String message ) {
       super(message);
   }
}
