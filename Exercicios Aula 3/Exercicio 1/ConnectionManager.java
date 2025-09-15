package br.edu.bra.ifsp;

interface ConnectionManager {
    void dial(String phoneNumber);
    void hangup();
}