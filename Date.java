/**
 * this class create date with valid information
 * @author Baruch magid
 * @version v.1.0
 */
public class Date 
{
    private static final int JAN=1;
    private static final int FEB=2;
    private static final int MAR=3;
    private static final int APR=4;
    private static final int MAY=5;
    private static final int JUN=6;
    private static final int JUL=7;
    private static final int AUG=8;
    private static final int SEP=9;
    private static final int OCT=10;
    private static final int NOV=11;
    private static final int DEC=12;
    private static final int MAX_DAY_FEB_LEAP_YEAR=29;
    private static final int MAX_DAY_FEB_REGULAR_YEAR=28;
    private static final int MAX_DAY_30=30;
    private static final int MAX_DAY_31=31;
    private static final int DEFAULT_DAY=1;
    private static final int DEFAULT_MONTH=1;
    private static final int DEFAULT_YEAR=2024;
    private static final int MIN_YEAR=1000;
    private static final int MAX_YEAR=9999;
    private static final int MIN_MONTH=1;
    private static final int MAX_MONTH=12;
    private static final int MIN_DAY=1;
    
    //instance variables
    private int _day;
    private int _month;
    private int _year;

    
/**
 * constructor of date object. 
 * will get 3 parameters: day, month and year. its chack if it valid 
 * date, and in ohter case, 
 * will return defoult value.
 * @param day, the day value of the date.
 * @param month, the month value of the dave
 * @param year. the year value of the year
 * 
 */
    public Date(int day, int month, int year)
    {
        //condition
        if(isValidDate(day,month,year)==true)//check valid date input
        {
            _day=day;
            _month=month;
            _year=year;
        }
        else
        {
            _day=DEFAULT_DAY;
            _month=DEFAULT_MONTH;
            _year=DEFAULT_YEAR;
        }
    }
/**
     * defoult constructor.
     * will retrun defoult values form the class for day month and year.

     */
    public Date()
    {
        _day=DEFAULT_DAY;
        _month=DEFAULT_MONTH;
        _year=DEFAULT_YEAR;
    }
/**
     * copy constructur for date object.
     * constructs an object with the same values as other date.
     * @param other The date object from which to construct the new object.
     */
    public Date (Date other)
    {
        if (other!=null)
        {
            this._year=other._year;
            this._month=other._month;
            this._day=other._day;
        }
    }
/**
     * return the day of the object
     * @return return the the day of the date.
     */
    public int getDay()
    {
        return _day;
    }
/**
     * return the month of the date
     * @return the month of the date
     */
    public int getMonth()
    {
        return _month;
    }
/**
     * return the year of the date
     * @return the year of the date
     */     
    public int getYear()//tested
    {
        return _year;
    }
/**
     * set the day of the date
     * @param dayToSet the new day of the object
     */  
    public void setDay(int dayToSet)
    {
        if(isValidDate(dayToSet,this._month,this._year)==true)//check valid date input
            _day=dayToSet;
    }
/**
     * set the month  of the date
     * @param monthToSet the new month of the object
     */ 
    public void setMonth(int monthToSet)
    {
        if(isValidDate(this._day,monthToSet,this._year)==true)//check valid date input
            _month=monthToSet;
    }
/**
     * set the year of the date
     * @param yearToSet the new month of the object
     */ 
    public void setYear(int yearToSet)
    {
        if(isValidDate(this._day,this._month,yearToSet)==true)//check valid date input
            _year=yearToSet;
    }
/**
     * check if the dates are equal
     * @param other The date of other date to be compered with.
     */
    public boolean equals (Date other)
    {
        return calculateDate(this._day, this._month , this._year) == calculateDate(other._day, other._month , other._year);//compare dates by numeric value by given algorithm
    }
/**
     * check if this date before other
     * @param other The date of other date to be compered with.
     */
    public boolean before (Date other)
    {
        return calculateDate(this._day, this._month , this._year) < calculateDate(other._day, other._month , other._year); //compare dates by numeric value by given algorithm   
    }
/**
     * check if this date after other date
     * @param other The date of other date to be compered with.
     */
    public boolean after (Date other)
    {
        return other.before(this); 
    }
/**
     * return tomorrow date
     * @return return tomorrow date
     */
    public Date tomorrow()//check for valid date, and chege the date according the stage.
    {
    
    Date tomorrow = new Date(this);
    if(isValidDate((tomorrow._day+=1),tomorrow._month,tomorrow._year)==true)
        tomorrow._day=tomorrow._day;
    else
    {
        if(isValidDate((tomorrow._day+=1),tomorrow._month+=1,tomorrow._year)==true)
        {
        tomorrow._day=DEFAULT_DAY;
        tomorrow._month=tomorrow._month;
        }
        else
        {
        tomorrow._day=DEFAULT_DAY;
        tomorrow._month=DEFAULT_MONTH;
        tomorrow._year+=1;
        }
    }
    return tomorrow;
}
/**
     * return the difference betwen two dates
     * @param other the other date object to be compered with.
     * @return return the difference between two dates.
     */
    public int difference (Date other)
    {
        int difference = Math.abs(calculateDate(this._day, this._month , this._year) - calculateDate(other._day, other._month , other._year));

        return difference;
    }
/**
     * return string of date object
     * @return retruns the date in particular structure dd/mm/yyyy
     */
    public String toString()
    {
        String s="";
        if (_day < 10)
            s+="0"+_day+"/";
        else 
            s+=_day+"/";
        if (_month < 10)
            s+="0"+_month+"/";
        else
            s+=_month+"/";
        s+=_year;
        
        return s;
  
    }
    
    //computes the day number since the beginning of the Christian counting of years
    //tested.
private int calculateDate ( int day, int month, int year) 
    {
        if (month < 3) 
        { year--;
            month = month + 12; 
        }
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62); 
    }
private boolean isValidDate(int day,int month, int year)//check year, then month then insert maximum days for checking
    {
        int maxDay=0;//local variable for max days in month
        
        if (isLeapYear(year)==true)//return days in month if leap year source: https://en.wikipedia.org/wiki/Month
        {
            switch(month)
                {
                case FEB:maxDay=MAX_DAY_FEB_LEAP_YEAR;
                    break;
                case JAN:
                case MAR:
                case MAY:
                case JUL: 
                case AUG:
                case OCT:
                case DEC:maxDay=MAX_DAY_31;
                    break;
                case APR: 
                case JUN:
                case SEP:
                case NOV:maxDay=MAX_DAY_30;
                    break;
                }
        }
        else
        {
            switch(month)//return days in month if NOT leap year
            {
                case FEB:maxDay=MAX_DAY_FEB_REGULAR_YEAR;
                    break;
                case JAN:
                case MAR:
                case MAY:
                case JUL: 
                case AUG:
                case OCT:
                case DEC:maxDay=MAX_DAY_31;
                    break;
                case APR: 
                case JUN:
                case SEP:
                case NOV:maxDay=MAX_DAY_30;
                    break;
            }
        }
        if(year >= MIN_YEAR  && year  <= MAX_YEAR  &&
          month >= MIN_MONTH && month <= MAX_MONTH &&
            day >= MIN_DAY   && day   <= maxDay       )
            return true;    
        return false;
    }
private boolean isLeapYear(int year)
{
    if (year % 4==0 && year % 100!=0 || year%400==0)//formula according https://he.wikipedia.org/wiki/שנה_מעוברת
        return true;
    return false;
}
}//end of class

