/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.acme.schooltimetabling.bootstrap;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.acme.schooltimetabling.domain.Lesson;
import org.acme.schooltimetabling.domain.Room;
import org.acme.schooltimetabling.domain.Timeslot;
import org.acme.schooltimetabling.domain.Teacher;
import org.acme.schooltimetabling.domain.Subject;
import org.acme.schooltimetabling.domain.StudentGroup;
import org.acme.schooltimetabling.persistence.LessonRepository;
import org.acme.schooltimetabling.persistence.RoomRepository;
import org.acme.schooltimetabling.persistence.TeacherRepository;
import org.acme.schooltimetabling.persistence.SubjectRepository;
import org.acme.schooltimetabling.persistence.StudentGroupRepository;
import org.acme.schooltimetabling.persistence.TimeslotRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class DemoDataGenerator {

    @ConfigProperty(name = "timeTable.demoData", defaultValue = "SMALL")
    DemoData demoData;

    @Inject
    TimeslotRepository timeslotRepository;
    @Inject
    RoomRepository roomRepository;
    @Inject
    LessonRepository lessonRepository;
    @Inject
    TeacherRepository teacherRepository;
    @Inject
    SubjectRepository subjectRepository;
    @Inject
    StudentGroupRepository studentGroupRepository;

    @Transactional
    public void generateDemoData(@Observes StartupEvent startupEvent) {
        if (demoData == DemoData.NONE) {
            return;
        }

        List<Timeslot> timeslotList = new ArrayList<>(10);
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)));

        timeslotList.add(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(8, 30), LocalTime.of(9, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(9, 30), LocalTime.of(10, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(10, 30), LocalTime.of(11, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(13, 30), LocalTime.of(14, 30)));
        timeslotList.add(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)));
        timeslotRepository.persist(timeslotList);

        List<Room> roomList = new ArrayList<>(3);
        roomList.add(new Room("Room A"));
        roomList.add(new Room("Room B"));
        roomList.add(new Room("Room C"));
        roomRepository.persist(roomList);

        List<Teacher> teacherList = new ArrayList<>(4);
        teacherList.add(new Teacher("A Turing"));
        teacherList.add(new Teacher("Y Kenshi"));
        teacherList.add(new Teacher("B Marly"));
        teacherList.add(new Teacher("J Smith"));
        teacherRepository.persist(teacherList);

        List<Subject> subjectList = new ArrayList<>(4);
        subjectList.add(new Subject("Biology"));
        subjectList.add(new Subject("Mathematics"));
        subjectList.add(new Subject("English"));
        subjectList.add(new Subject("Physics"));
        subjectRepository.persist(subjectList);

        List<StudentGroup> studentGroupList = new ArrayList<>(4);
        studentGroupList.add(new StudentGroup("9th Grade"));
        studentGroupList.add(new StudentGroup("10th Grade"));
        studentGroupList.add(new StudentGroup("11th Grade"));
        studentGroupList.add(new StudentGroup("12th Grade"));
        studentGroupRepository.persist(studentGroupList);

        List<Lesson> lessonList = new ArrayList<>();
        lessonList.add(new Lesson("19", "14", "22", timeslotList.get(0), roomList.get(0)));
        lessonList.add(new Lesson("20", "15", "23", timeslotList.get(0), roomList.get(1)));
        lessonList.add(new Lesson("18", "16", "24", timeslotList.get(0), roomList.get(2)));
        lessonList.add(new Lesson("21", "14", "23", timeslotList.get(1), roomList.get(0)));
        lessonList.add(new Lesson("19", "16", "25", timeslotList.get(1), roomList.get(2)));
        lessonList.add(new Lesson("18", "15", "23", timeslotList.get(1), roomList.get(1)));
        lessonList.add(new Lesson("21", "15", "22", timeslotList.get(2), roomList.get(1)));
        lessonList.add(new Lesson("19", "14", "23", timeslotList.get(0), roomList.get(2)));
        lessonList.add(new Lesson("20", "17", "24", timeslotList.get(3), roomList.get(0)));
        lessonList.add(new Lesson("18", "15", "23", timeslotList.get(4), roomList.get(1)));
        lessonList.add(new Lesson("21", "14", "23", timeslotList.get(3), roomList.get(2)));
        // lessonList.add(new Lesson("Physics", "M. Curie", "9th grade"));
        // lessonList.add(new Lesson("Chemistry", "M. Curie", "9th grade"));
        // lessonList.add(new Lesson("Biology", "C. Darwin", "9th grade"));
        // lessonList.add(new Lesson("History", "I. Jones", "9th grade"));
        // lessonList.add(new Lesson("English", "I. Jones", "9th grade"));
        // lessonList.add(new Lesson("English", "I. Jones", "9th grade"));
        // lessonList.add(new Lesson("Spanish", "P. Cruz", "9th grade"));
        // lessonList.add(new Lesson("Spanish", "P. Cruz", "9th grade"));  
        // if (demoData == DemoData.LARGE) {
        //     lessonList.add(new Lesson("Math", "A. Turing", "9th grade"));
        //     lessonList.add(new Lesson("Math", "A. Turing", "9th grade"));
        //     lessonList.add(new Lesson("Math", "A. Turing", "9th grade"));
        //     lessonList.add(new Lesson("ICT", "A. Turing", "9th grade"));
        //     lessonList.add(new Lesson("Physics", "M. Curie", "9th grade"));
        //     lessonList.add(new Lesson("Geography", "C. Darwin", "9th grade"));
        //     lessonList.add(new Lesson("Geology", "C. Darwin", "9th grade"));
        //     lessonList.add(new Lesson("History", "I. Jones", "9th grade"));
        //     lessonList.add(new Lesson("English", "I. Jones", "9th grade"));
        //     lessonList.add(new Lesson("Drama", "I. Jones", "9th grade"));
        //     lessonList.add(new Lesson("Art", "S. Dali", "9th grade"));
        //     lessonList.add(new Lesson("Art", "S. Dali", "9th grade"));
        //     lessonList.add(new Lesson("Physical education", "C. Lewis", "9th grade"));
        //     lessonList.add(new Lesson("Physical education", "C. Lewis", "9th grade"));
        //     lessonList.add(new Lesson("Physical education", "C. Lewis", "9th grade"));
        // }

        // lessonList.add(new Lesson("Math", "A. Turing", "10th grade"));
        // lessonList.add(new Lesson("Math", "A. Turing", "10th grade"));
        // lessonList.add(new Lesson("Math", "A. Turing", "10th grade"));
        // lessonList.add(new Lesson("Physics", "M. Curie", "10th grade"));
        // lessonList.add(new Lesson("Chemistry", "M. Curie", "10th grade"));
        // lessonList.add(new Lesson("French", "M. Curie", "10th grade"));
        // lessonList.add(new Lesson("Geography", "C. Darwin", "10th grade"));
        // lessonList.add(new Lesson("History", "I. Jones", "10th grade"));
        // lessonList.add(new Lesson("English", "P. Cruz", "10th grade"));
        // lessonList.add(new Lesson("Spanish", "P. Cruz", "10th grade"));
        // if (demoData == DemoData.LARGE) {
        //     lessonList.add(new Lesson("Math", "A. Turing", "10th grade"));
        //     lessonList.add(new Lesson("Math", "A. Turing", "10th grade"));
        //     lessonList.add(new Lesson("ICT", "A. Turing", "10th grade"));
        //     lessonList.add(new Lesson("Physics", "M. Curie", "10th grade"));
        //     lessonList.add(new Lesson("Biology", "C. Darwin", "10th grade"));
        //     lessonList.add(new Lesson("Geology", "C. Darwin", "10th grade"));
        //     lessonList.add(new Lesson("History", "I. Jones", "10th grade"));
        //     lessonList.add(new Lesson("English", "P. Cruz", "10th grade"));
        //     lessonList.add(new Lesson("English", "P. Cruz", "10th grade"));
        //     lessonList.add(new Lesson("Drama", "I. Jones", "10th grade"));
        //     lessonList.add(new Lesson("Art", "S. Dali", "10th grade"));
        //     lessonList.add(new Lesson("Art", "S. Dali", "10th grade"));
        //     lessonList.add(new Lesson("Physical education", "C. Lewis", "10th grade"));
        //     lessonList.add(new Lesson("Physical education", "C. Lewis", "10th grade"));
        //     lessonList.add(new Lesson("Physical education", "C. Lewis", "10th grade"));

        //     lessonList.add(new Lesson("Math", "A. Turing", "11th grade"));
        //     lessonList.add(new Lesson("Math", "A. Turing", "11th grade"));
        //     lessonList.add(new Lesson("Math", "A. Turing", "11th grade"));
        //     lessonList.add(new Lesson("Math", "A. Turing", "11th grade"));
        //     lessonList.add(new Lesson("Math", "A. Turing", "11th grade"));
        //     lessonList.add(new Lesson("ICT", "A. Turing", "11th grade"));
        //     lessonList.add(new Lesson("Physics", "M. Curie", "11th grade"));
        //     lessonList.add(new Lesson("Chemistry", "M. Curie", "11th grade"));
        //     lessonList.add(new Lesson("French", "M. Curie", "11th grade"));
        //     lessonList.add(new Lesson("Physics", "M. Curie", "11th grade"));
        //     lessonList.add(new Lesson("Geography", "C. Darwin", "11th grade"));
        //     lessonList.add(new Lesson("Biology", "C. Darwin", "11th grade"));
        //     lessonList.add(new Lesson("Geology", "C. Darwin", "11th grade"));
        //     lessonList.add(new Lesson("History", "I. Jones", "11th grade"));
        //     lessonList.add(new Lesson("History", "I. Jones", "11th grade"));
        //     lessonList.add(new Lesson("English", "P. Cruz", "11th grade"));
        //     lessonList.add(new Lesson("English", "P. Cruz", "11th grade"));
        //     lessonList.add(new Lesson("English", "P. Cruz", "11th grade"));
        //     lessonList.add(new Lesson("Spanish", "P. Cruz", "11th grade"));
        //     lessonList.add(new Lesson("Drama", "P. Cruz", "11th grade"));
        //     lessonList.add(new Lesson("Art", "S. Dali", "11th grade"));
        //     lessonList.add(new Lesson("Art", "S. Dali", "11th grade"));
        //     lessonList.add(new Lesson("Physical education", "C. Lewis", "11th grade"));
        //     lessonList.add(new Lesson("Physical education", "C. Lewis", "11th grade"));
        //     lessonList.add(new Lesson("Physical education", "C. Lewis", "11th grade"));

        //     lessonList.add(new Lesson("Math", "A. Turing", "12th grade"));
        //     lessonList.add(new Lesson("Math", "A. Turing", "12th grade"));
        //     lessonList.add(new Lesson("Math", "A. Turing", "12th grade"));
        //     lessonList.add(new Lesson("Math", "A. Turing", "12th grade"));
        //     lessonList.add(new Lesson("Math", "A. Turing", "12th grade"));
        //     lessonList.add(new Lesson("ICT", "A. Turing", "12th grade"));
        //     lessonList.add(new Lesson("Physics", "M. Curie", "12th grade"));
        //     lessonList.add(new Lesson("Chemistry", "M. Curie", "12th grade"));
        //     lessonList.add(new Lesson("French", "M. Curie", "12th grade"));
        //     lessonList.add(new Lesson("Physics", "M. Curie", "12th grade"));
        //     lessonList.add(new Lesson("Geography", "C. Darwin", "12th grade"));
        //     lessonList.add(new Lesson("Biology", "C. Darwin", "12th grade"));
        //     lessonList.add(new Lesson("Geology", "C. Darwin", "12th grade"));
        //     lessonList.add(new Lesson("History", "I. Jones", "12th grade"));
        //     lessonList.add(new Lesson("History", "I. Jones", "12th grade"));
        //     lessonList.add(new Lesson("English", "P. Cruz", "12th grade"));
        //     lessonList.add(new Lesson("English", "P. Cruz", "12th grade"));
        //     lessonList.add(new Lesson("English", "P. Cruz", "12th grade"));
        //     lessonList.add(new Lesson("Spanish", "P. Cruz", "12th grade"));
        //     lessonList.add(new Lesson("Drama", "P. Cruz", "12th grade"));
        //     lessonList.add(new Lesson("Art", "S. Dali", "12th grade"));
        //     lessonList.add(new Lesson("Art", "S. Dali", "12th grade"));
        //     lessonList.add(new Lesson("Physical education", "C. Lewis", "12th grade"));
        //     lessonList.add(new Lesson("Physical education", "C. Lewis", "12th grade"));
        //     lessonList.add(new Lesson("Physical education", "C. Lewis", "12th grade"));
        //}

        Lesson lesson = lessonList.get(0);
        lesson.setTimeslot(timeslotList.get(0));
        lesson.setRoom(roomList.get(0));

        lessonRepository.persist(lessonList);
    }

    public enum DemoData {
        NONE,
        SMALL,
        LARGE
    }

}
