package com.cinemaproject.cinemaproject.Model;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AdditionalServiceImplTest{

    AdditionalServiceImpl additionalServiceImpl = new AdditionalServiceImpl();


    @Test
    void createToken() throws Exception {
    assertNotEquals(additionalServiceImpl.createToken(), null);
    }

    @Test
    void isSeatNextTo() throws Exception {

        List<ReservedSeat> list = new List<ReservedSeat>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<ReservedSeat> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(ReservedSeat reservedSeat) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends ReservedSeat> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends ReservedSeat> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public ReservedSeat get(int index) {
                return null;
            }

            @Override
            public ReservedSeat set(int index, ReservedSeat element) {
                return null;
            }

            @Override
            public void add(int index, ReservedSeat element) {

            }

            @Override
            public ReservedSeat remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<ReservedSeat> listIterator() {
                return null;
            }

            @Override
            public ListIterator<ReservedSeat> listIterator(int index) {
                return null;
            }

            @Override
            public List<ReservedSeat> subList(int fromIndex, int toIndex) {
                return null;
            }
        };

        assertFalse(additionalServiceImpl.isSeatNextTo(list,  1));

    }

    @Test
    void isSeatReservedAlready() {
    }
}