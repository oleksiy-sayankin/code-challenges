package net.javacogito.workingexperience;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Main {

  public static final String EMPTY = "";

  public static void main(String[] args) throws IOException, ParseException {
    File file = new File(args[0]);
    BufferedReader buffer = new BufferedReader(new FileReader(file));
    String inputLine;
    while ((inputLine = buffer.readLine()) != null) {
      if (!EMPTY.equals(inputLine.trim())) {
        System.out.println(findExperience(parseInput(inputLine)));
      }
    }
  }

  private static List<Interval> parseInput(String data) throws ParseException {
    String[] intervalsAsStrings = data.split("; ");
    List<Interval> intervals = new ArrayList<>();

    for(String intervalsAsString : intervalsAsStrings){
      intervals.add(new Interval(intervalsAsString));
    }
    Collections.sort(intervals);
    return intervals;
  }

  private static int findExperience(List<Interval> intervals) {
    List<Interval> mergedIntervals = new ArrayList<>();
    Interval mergedInterval = intervals.get(0);
    Interval currentInterval = intervals.get(0);
    int size = intervals.size();

    for(int i = 0; i <= size - 2; i++){
      Interval nextInterval = intervals.get(i + 1);
      boolean isLast = i == size - 2;

      if(currentInterval.hasIntersection(nextInterval)){
        mergedInterval = currentInterval.merge(nextInterval);
        currentInterval = mergedInterval;
        if(isLast){
          mergedIntervals.add(currentInterval);
        }
      } else{
        mergedIntervals.add(currentInterval);
        currentInterval = nextInterval;
        if(isLast){
          mergedIntervals.add(currentInterval);
        }
      }
    }
    int totalMonths = 0;
    for(Interval interval : mergedIntervals){
      totalMonths += interval.lengthInMonths();
    }

    return totalMonths / 12;
  }


  public static class Interval implements Comparable<Interval>{
    private int startMonth;
    private int startYear;
    private int endMonth;
    private int endYear;

    Interval(String data) throws ParseException {
      String[] rawData = data.split("-");
      String start = rawData[0];
      String end = rawData[1];
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(start));
      startMonth = calendar.get(Calendar.MONTH);
      startYear = calendar.get(Calendar.YEAR);
      calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(end));
      endMonth = calendar.get(Calendar.MONTH);
      endYear = calendar.get(Calendar.YEAR);
    }

    Interval(int startMonth, int startYear, int endMonth, int endYear) {
      this.startMonth = startMonth;
      this.startYear = startYear;
      this.endMonth = endMonth;
      this.endYear = endYear;
    }


    int lengthInMonths(){
      if(endYear - startYear >= 2){
        return (endYear - startYear - 1) * 12 + (13 - startMonth + endMonth);
      }

      if(endYear - startYear == 1){
        return 13 - startMonth + endMonth;
      }

      return endMonth - startMonth + 1;
    }

    int startInMonths(){
      return startYear * 12 +  startMonth + 1;
    }

    int endInMonths(){
      return endYear * 12 +  endMonth + 1;
    }

    boolean hasIntersection(Interval other){
      if(other.startInMonths() >= startInMonths() &&  other.startInMonths() <= endInMonths()){
        return true;
      }

      if(other.endInMonths() >= startInMonths() &&  other.endInMonths() <= endInMonths()){
        return true;
      }

      if(other.startInMonths() >= startInMonths() && other.endInMonths() <= endInMonths()){
        return true;
      }

      if(other.startInMonths() >= startInMonths() && other.endInMonths() <= endInMonths()){
        return true;
      }

      if(startInMonths() >= other.startInMonths() && endInMonths() <= other.endInMonths()){
        return true;
      }

      return false;
    }

    Interval merge(Interval other){
      int newStartMonth = other.startMonth;
      int newStartYear = other.startYear;
      int newEndMonth = other.endMonth;
      int newEndYear = other.endYear;

      if(this.startYear < other.startYear){
        newStartMonth = this.startMonth;
        newStartYear = this.startYear;
      }

      if(this.startYear == other.startYear){
        if(this.startMonth < other.startMonth)
        newStartMonth = this.startMonth;
      }

      if(this.endYear > other.endYear){
        newEndMonth = this.endMonth;
        newEndYear = this.endYear;
      }

      if(this.endYear == other.endYear){
        if(this.endMonth > other.endMonth)
          newEndMonth = this.endMonth;
      }

      return new Interval(newStartMonth, newStartYear, newEndMonth, newEndYear);
    }

    @Override
    public int compareTo(Interval other) {
      if(this.startMonth == other.startMonth && this.startYear == other.startYear){
        return endYear * 12 + endMonth - (other.endYear * 12 + other.endMonth);
      }
      return startYear * 12 + startMonth - (other.startYear * 12 + other.startMonth);
    }

    @Override
    public String toString(){
      return (startMonth + 1) + "." + startYear + " - " + (endMonth + 1) + "." + endYear;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Interval interval = (Interval) o;

      if (startMonth != interval.startMonth) return false;
      if (startYear != interval.startYear) return false;
      if (endMonth != interval.endMonth) return false;
      return endYear == interval.endYear;
    }

    @Override
    public int hashCode() {
      int result = startMonth;
      result = 31 * result + startYear;
      result = 31 * result + endMonth;
      result = 31 * result + endYear;
      return result;
    }
  }
}