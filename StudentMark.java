import java.util.*;
import java.io.*;

class StudentMark{

	private class Subjects{
		private String subjectName;
		private double subjectmarks;

		private void setSubjectName(String subject){
			subjectName = subject;
		}
		private String getSubjectName(){
			return subjectName;
		}
		private void setEnglishMarks(double term, double midmark, double final){
			double finalMarks = (term*(30.0/100.0))+(midmark*(30.0/100.0))+(final*(40.0/100.0));
			marks = finalMarks;
		}
		private void setScienceMarks(double attendance, double project, double midmark, double final){
			double finalMarks = (attendance*(10.0/100.0))+(project*(30.0/100.0))+(midmark*(30.0/100.0))+(final*(30.0/100.0));
			marks = finalMarks;
		}
		private void setMathMarks(double quarter1, double quarter2, double quarter3, double quarter4, double quarter5, double test1, double test2, double final){
			double quizAverage = (quarter1+quarter2+quarter3+quarter4+quarter5)/5.0;
			double finalMarks = (quizAverage*(15.0/100.0))+(test1*(30.0/100.0))+(test2*(20.0/100.0))+(final*(35.0/100.0));
			marks = finalMarks;
		}
		private double getMarks(){
			return marks;
		}
	}

	private String firName;
	private String laName;
	private Subject subject = new Subject();
	private char performance;

	private void setName(String firstName, String lastName){
		firName = firstName;
		laName = lastName;
	}
	private String getName(){
		String name = firName+" "+laName;
		return name;
	}
	private void setSubject(String sub){
		subject.setSubjectName(sub);
	}
	private String getSubject(){
		return subject.getSubjectName();
	}
	private void setEnglish(double term, double midmark, double final){
		subject.setEnglishMarks(term,midmark,final);
	}
	private void setScience(double attendance, double project, double midmark, double final){
		subject.setScienceMarks(attendance,project,midmark,final);
	}
	private void setMath(double q1, double q2, double q3, double q4, double q5, double test1, double test2, double final){
		subject.setMathMarks(q1,q2,q3,q4,q5,test1,test2,final);
	}
	private double getMarks(){
		return subject.getMarks();
	}
	private char getGrade(){
		char grade = ' ';
		double marks = subject.getMarks();
		if(marks >= 90){
			grade = 'A';
		}else if(marks >= 80){
			grade = 'B';
		}else if(marks >= 70){
			grade = 'C';
		}else if(marks >= 60){
			grade = 'D';
		}else if(marks >= 50){
			grade = 'E';
		}else if(marks >= 40){
			grade = 'F';
		}else if(marks >= 30){
			grade = 'G';
		}else if(marks >= 20){
			grade = 'H';
		}else if(marks >= 10){
			grade = 'I';
		}else if(marks >= 0){
			grade = 'J';
		}else{
			grade = '-';
		}
		return grade;
	}

	public static void main(String[] args){
		int studentCount;
		int inputCounter = 0;
		int count = 2;
		//open and read input file
		FileInputStream fis = null;
		InputStreamReader isr;
		BufferedReader br;
		String line = "";
		try{
			fis = new FileInputStream("challenge4.txt");
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			line = br.readLine();
			studentCount = Integer.parseInt(line);
			Student[] array = new Student[studentCount];
			for(int i = 0; i < studentCount; i++){
				array[i] = new Student();
			}
			line = br.readLine();
			while(line != null || inputCounter < studentCount){//
				String[] splitLine;
				splitLine = line.split(",");
				if(count%2 == 0){
					//enter name details
					array[inputCounter].setName(splitLine[1],splitLine[0]);
				}
				else{
					//enter subject and marks
					array[inputCounter].setSubject(splitLine[0]);
					if(splitLine[0].equals("english")){
						array[inputCounter].setEnglish(Integer.parseInt(splitLine[1]),Integer.parseInt(splitLine[2]),Integer.parseInt(splitLine[3]));
					}
					else if(splitLine[0].equals("science")){
						array[inputCounter].setScience(Integer.parseInt(splitLine[1]),Integer.parseInt(splitLine[2]),Integer.parseInt(splitLine[3]),Integer.parseInt(splitLine[4]));
					}
					else if(splitLine[0].equals("math")){
						array[inputCounter].setMath(Integer.parseInt(splitLine[1]),Integer.parseInt(splitLine[2]),Integer.parseInt(splitLine[3]),Integer.parseInt(splitLine[4]),Integer.parseInt(splitLine[5]),Integer.parseInt(splitLine[6]),Integer.parseInt(splitLine[7]),Integer.parseInt(splitLine[8]));
					}
					inputCounter+=1;
				}
				count+=1;
				line = br.readLine();
			}
			fis.close();

			int[] gradeArray = new int[10];
			for(int i = 0; i < 10; i++){
				gradeArray[i] = 0;
			}
			//Export to output file
			FileOutputStream fos = null;
			PrintWriter pw;
			Formatter e;
		
			fos = new FileOutputStream("challenge4output.txt");
			pw = new PrintWriter(fos);
			pw.printf("| %-55s | %-10s | %7s | %-5s|\n","NAME","SUBJECT","MARKS","GRADE");
			for(int ii = 0; ii < array.length; ii++){
				e = new Formatter();
				e.format("| %-55s | %-10s | %7s | %5s|",array[ii].getName(),array[ii].getSubject(),array[ii].getMarks(),array[ii].getGrade());
				//
				switch(array[ii].getGrade()){
					case 'A':
						gradeArray[0]+=1;
						break;
					case 'B':
						gradeArray[1]+=1;
						break;
					case 'C':
						gradeArray[2]+=1;
						break;
					case 'D':
						gradeArray[3]+=1;
						break;
					case 'E':
						gradeArray[4]+=1;
						break;
					case 'F':
						gradeArray[5]+=1;
						break;
					case 'G':
						gradeArray[6]+=1;
						break;
					case 'H':
						gradeArray[7]+=1;
						break;
					case 'I':
						gradeArray[8]+=1;
						break;
					case 'J':
						gradeArray[9]+=1;
						break;
					default:
						break;
				}
				//
				pw.println(e);
			}
			pw.println("\nA: "+gradeArray[0]);
			pw.println("B: "+gradeArray[1]);
			pw.println("C: "+gradeArray[2]);
			pw.println("D: "+gradeArray[3]);
			pw.println("E: "+gradeArray[4]);
			pw.println("F: "+gradeArray[5]);
			pw.println("G: "+gradeArray[6]);
			pw.println("H: "+gradeArray[7]);
			pw.println("I: "+gradeArray[8]);
			pw.println("J: "+gradeArray[9]);
			pw.close();
		}
		catch(IOException e){
			if(fis != null){
				try{
					fis.close();
				}
				catch(IOException ee){
				}
			}
		}
	}
}