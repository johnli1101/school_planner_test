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

package org.acme.schooltimetabling.domain;

import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.SolverStatus;

@PlanningSolution
public class TimeTable {

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "timeslotRange")
    private List<Timeslot> timeslotList;
    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "roomRange")
    private List<Room> roomList;
    @PlanningEntityCollectionProperty
    private List<Lesson> lessonList;
    private List<Teacher> teacherList;
    private List<Subject> subjectList;
    private List<StudentGroup> studentGroupList;

    @PlanningScore
    private HardSoftScore score;

    // Ignored by OptaPlanner, used by the UI to display solve or stop solving button
    private SolverStatus solverStatus;

    // No-arg constructor required for OptaPlanner
    public TimeTable() {
    }

    public TimeTable(List<Timeslot> timeslotList, List<Room> roomList, List<Lesson> lessonList, List<Teacher> teacherList, List<Subject> subjectList, List<StudentGroup> studentGroupList) {
        this.timeslotList = timeslotList;
        this.roomList = roomList;
        this.lessonList = lessonList;
        this.teacherList = teacherList;
        this.subjectList = subjectList;
        this.studentGroupList = studentGroupList;
    }

    // ************************************************************************
    // Getters and setters
    // ************************************************************************

    public List<Timeslot> getTimeslotList() {
        return timeslotList;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public List<StudentGroup> getStudentGroupList() {
        return studentGroupList;
    }

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public SolverStatus getSolverStatus() {
        return solverStatus;
    }

    public void setSolverStatus(SolverStatus solverStatus) {
        this.solverStatus = solverStatus;
    }

}
