//<<<<<<< HEAD
////package com.example.gcccyclingapp;
////
////import android.content.Context;
////import android.database.sqlite.SQLiteDatabase;
////
////import org.junit.Rule;
////import org.junit.Test;
////import org.junit.runner.RunWith;
////import org.mockito.Mock;
////import org.mockito.junit.MockitoJUnitRunner;
////
////
////import org.junit.Test;
////import org.junit.runner.RunWith;
////import org.junit.Test;
////import static org.junit.Assert.*;
////
////import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
////
////import org.mockito.Mock;
////
////@RunWith(MockitoJUnitRunner.class)
////public class addAwardToParticipantTest {
////
////    public addAwardToParticipantTest() {
////    }
////
////    @Rule
////    public InstantTaskExecutorRule instantTaskExecutorRule =
////            new InstantTaskExecutorRule();
////    @Mock
////    Context mMockContext;
////    @Mock
////    SQLiteDatabase db;
////    @Test
////    public void addAwardToParticipantTest() {
////
////        DBAdmin dbAdmin = new DBAdmin(mMockContext);
////        db = dbAdmin.getWritableDatabase();
////
////        assertTrue(dbAdmin.addAwardToParticipant("bob", "sam"));
////
////    }
////}
////
//=======
//package com.example.gcccyclingapp;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.when;
//
//import org.mockito.Mock;
//
//@RunWith(MockitoJUnitRunner.class)
//
//
//public class addAwardToParticipantTest {
//
//    public addAwardToParticipantTest() {
//    }
//
//    @Mock
//    Context mMockContext;
//    @Mock
//    SQLiteDatabase db;
//    @Mock
//    SQLiteOpenHelper mockSQLiteOpenHelper;
//
//    @Before
//    public void setup() {
//        when(mockSQLiteOpenHelper.getWritableDatabase()).thenReturn(db);
//    }
//    @Test
//    public void addAwardToParticipantTest() {
//
//        DBAdmin dbAdmin = new DBAdmin(mMockContext, mockSQLiteOpenHelper);
////        db = dbAdmin.getWritableDatabase();
//
//        assertTrue(dbAdmin.addAwardToParticipant("bob", "sam"));
//
//    }
//}
//
//>>>>>>> d32fa70d75d754f3459a488cd1dc3da87c661dec
