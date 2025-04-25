/**
 * this class create baby objects for common use
 * @author Baruch Magid 
 * @version v.1.0
 */
public class Baby 
{
    private static final int MIN_GRAMS=0;
    private static final int MAX_GRAMS=999;
    private static final int MIN_KILOS=1;
    private static final int KILO_TO_GRAM=1000;
    private static final int MAX_ID_LENGTH=9;
    private static final int DAYS_IN_YEAR=365;
    private static final int MIN_DAY=1;
    private static final int WEEK=7;
    private static final int MONTH=30;
    //instance variables
    private String _firtsName;
    private String _lastName;
    private String _id;
    private Date _dateOfBirth;
    private Weight _birthWeight;
    private Weight _currentWeight;
    
    //constants
    private String DEFAULUT_ID="000000000";
    
    /**
     * constructor for baby object
     * @param fName, the fName of the object
     * @param lName, the lName of the object
     * @param id ,the id of the object
     * @param day, the day of birth of the object
     * @param month the month of birth of the object
     * @param year the year of birth of the object
     * @param birthWeightInGrams the birthWeightInGrams of the object.
     */
    public Baby(String fName, String lName, String id,int day, int month, int year, int birthWeightInGrams)
    {
        _firtsName = fName;
        _lastName = lName;
        _id = id;
        if (id.length() > MAX_ID_LENGTH)
            _id=DEFAULUT_ID;
        _dateOfBirth = new Date (day,month,year);
        _birthWeight = new Weight (birthWeightInGrams);
        _currentWeight = _birthWeight;
    }
    /**
     * copy constructur of baby object.
     * constructs an object with the same values as other baby.
     * @param other The baby object from which to construct the new object.
     */
    public Baby(Baby other)
    {
        this._firtsName = other._firtsName;
        this._lastName = other._lastName;
        this._id = other._id;
        this._dateOfBirth = other._dateOfBirth;
        this._birthWeight = other._birthWeight;
        this._currentWeight = other._currentWeight;
    }
    /**
     * return the first name of the object
     * @return return the first name of the object
     */
    public String getFirstName()
    {
        return _firtsName;
    }
    /**
     * return the last name of the object
     * @return return the last name of the object
     */
    public String getLastName()
    {
        return _lastName;
    }
    /**
     * return the id of the object
     * @return return the id of the object
     */
    public String getId()
    {
        return _id;
    }
    /**
     * return the date of birth of the object
     * @return return the date of birth of the object
     */
    public Date getDateOfBirth() 
    {
        return _dateOfBirth;
    }
    /**
     * return the birth weight of the object
     * @return return the birth weight of the object
     */
    public Weight getBirthWeight()
    {
        return _birthWeight;
    }
    /**
     * return the current weight of the object
     * @return return the current weight of the object
     */
    public Weight getCurrentWeight()
    {
        return _currentWeight;
    }
    /**
     * set current weight of the object if summerized grams > then 1000 grams.
     * @param weightToSet the new weight of the object
     */
    public void setCurrentWeight(Weight weightToSet)
    {
        int k = weightToSet.getKilos();
        int g = weightToSet.getGrams();
        int common = k * KILO_TO_GRAM + g;
        if(common >= KILO_TO_GRAM)
            _currentWeight = weightToSet;
    }
        /**
     * return string of baby object
     * @retrun return the string of the object
     */
    public String toString()
    {
        String s;
        s="";
        s="Name: "+_firtsName + " "+ _lastName;
        s+="\n\n"+"Id: "+_id;
        s+="\n\n"+"Date of Birth: "+ _dateOfBirth;
        s+="\n\n"+"Birth Weight: " + _birthWeight;
        s+="\n\n"+"Current Weight: " + _currentWeight;
        s+="\n";
        
        return s;
    }
    /**
     * return if this object equals with other object
     * compere first name, last name, id and date of birth.
     * @return return if this object equals with other object
     */
    public boolean equals (Baby other)
    {
        return (this._firtsName.equals(other._firtsName)     &&
                this._lastName.equals(other._lastName)       &&
                this._dateOfBirth.equals(other._dateOfBirth) &&
                this._id.equals(other._id)          );
    }
    /**
     * return if this object twin with other object
     * compere first name, last name, id and date of birth.
     * @return return if those objects are twins
     */
    public boolean areTwins (Baby other)
    {
        return (this._lastName.equals(other._lastName)       &&
               !this._firtsName.equals(other._firtsName)     &&
               !this._id.equals(other._id)                   &&
               (this._dateOfBirth.equals(other._dateOfBirth) || 
                this._dateOfBirth.difference(other._dateOfBirth) <=1));
               
        
    }
    /**
     * return if first baby object heavier then other
     * @return return if first baby object heavier then other
     */
    public boolean heavier (Baby other)
    {
        int firstWeight = this._currentWeight.getKilos()*KILO_TO_GRAM + this._currentWeight.getGrams();
        int secondWeight = other._currentWeight.getKilos()*KILO_TO_GRAM + other._currentWeight.getGrams();
        return firstWeight > secondWeight;
    }
    /**
     * return if this baby object is older the other
     * @return if this baby object is older the other
     */
    public boolean older (Baby other)
    {
        return this._dateOfBirth.before(other._dateOfBirth);
    }
    /**
     * updeate current weight of object 
     * in condition there final weight > 1 kilo.
     * @param grams, the grams to be added.
     */
    public void updateCurrentWeight (int grams)
    {
        int common = this._currentWeight.getKilos()*KILO_TO_GRAM + this._currentWeight.getGrams() + grams;
        Weight w = new Weight(common);
        if(common >= KILO_TO_GRAM)
            _currentWeight = w;
    }
    /**
     * check if the weight in valid range, according given alogorithm.
     * @param numOfDays the numOfDays from birth till check date.
     * @return return value 1 for invalid input, 2 for bad progressivness and 3 for regular.
     */
    public int isWeightInValidRange (int numOfDays)
    {
        int output= 1;
        double startWeight = this._birthWeight.getKilos()*KILO_TO_GRAM + this._birthWeight.getGrams();//convert weight into one unit of grams
        double endWeight = this._currentWeight.getKilos()*KILO_TO_GRAM + this._currentWeight.getGrams();//the same
        double fisrtWeekPercent = 10;
        double percentForDay = 10/7;
        if (numOfDays > DAYS_IN_YEAR || numOfDays < MIN_DAY)//check for invalid input
            output= 1;
        //end of 1/6 condition
        if (numOfDays >= MIN_DAY && numOfDays <= WEEK)//check first week, get percent from epecific day.
        {
            switch(numOfDays)
            {
                case 1: fisrtWeekPercent = percentForDay * 1;
                    break;
                case 2: fisrtWeekPercent = percentForDay * 2;
                    break;
                case 3: fisrtWeekPercent = percentForDay * 3;
                    break;
                case 4: fisrtWeekPercent = percentForDay * 4;
                    break;
                case 5: fisrtWeekPercent = percentForDay * 5;
                    break;
                case 6: fisrtWeekPercent = percentForDay * 6;
                    break;
                case 7: fisrtWeekPercent = percentForDay * 7;
                    break;
            }
            if(endWeight < (startWeight - startWeight * (fisrtWeekPercent / 100)))//check each day according given algorithem
                output= 2;
            else
                output= 3;
        }//end of 2/6 condition
        if (numOfDays > WEEK && numOfDays <= MONTH*2)//check first 2 months according given algorithem
        {
            if(endWeight < (startWeight - startWeight * (10 / 100) + (numOfDays-7) * 30))
                output= 2;
            else
                output= 3;   
        }//end of 3/6 condition
        if (numOfDays >  MONTH*2 && numOfDays <= MONTH*4)//check first 4 months according given algorithem
        {
            if(endWeight < startWeight - startWeight * (10 / 100) + 53 * 30 + (numOfDays - 60) * 25)
                output= 2;
            else
                output= 3;
        }//end of 4/6 condition
        if (numOfDays >  MONTH*4 && numOfDays <= MONTH*8)//check first 8 months according given algorithem
        {
            if(endWeight < startWeight - startWeight * (10 / 100) + 53 * 30 + 60 * 25 + (numOfDays - 120) * 16) 
                output= 2;
            else
                output= 3;
        }//end of 5/6 condition
        if (numOfDays > MONTH*8  && numOfDays <= DAYS_IN_YEAR)//check for 12 months according given algorithem
        {
            if(endWeight < startWeight - startWeight * (10 / 100) + 53 * 30 + 60 * 25 + 120 * 16 + (numOfDays - 240)*8 ) 
                output= 2;
            else
                output= 3;
        }//end of 6/6 condition
        return output;
    }//end of isWeightInValidRange method
}//end of baby class