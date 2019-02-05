package net.cheltsov.library.action;

import net.cheltsov.library.exception.LibraryException;

public interface BasicAction {
    LibraryResponse executeAction(LibraryRequest request) throws LibraryException;
}
