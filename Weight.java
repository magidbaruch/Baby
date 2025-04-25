/**
 * this class create weight objects for common use and chack for valid input
 * @author Baruch Magid
 * @version v.1.0
 */
public class Weight 
{
    private static final int MIN_VAL=0;
    //constants
    private static final int MIN_GRAMS=0;
    private static final int MAX_GRAMS=999;
    private static final int MIN_KILOS=1;
    
    //instance variables
    private int _kilos;
    private int _grams;
    

    
    /**
     * constructor of weight object.
     * will check for valid input, overwise will construct defoult object.
     * @param kilos the kilos of object.
     * @param grams the grams of object.
     */
    public Weight(int kilos, int grams)
    {
        if(isValidWeight(kilos,grams))//check for correct input
        {
            _kilos=kilos;
            _grams=grams;
        }
        else
        {
            _kilos=MIN_KILOS;
            _grams=MIN_GRAMS;
        }
    }
    /**
     * copy constructor
     * constructs an object with the same values as other weight.
     * @param other The weight object from which to construct the new object.
     */
    public Weight (Weight other)
    {
        this._kilos = other._kilos;
        this._grams = other._grams;
    }
    /**
     * constructor of wieght object with one input of grams
     * it will recalculte to kilos and grams for common print structur.
     * @param totalGrams The totalGrams of the object.
     */
    public Weight(int totalGrams)
    {
        if (totalGrams > (MAX_GRAMS+1))//check for correct input
        {
            _grams=totalGrams % 1000;
            _kilos=totalGrams / 1000;
        }
        else
        {
            _grams=MIN_GRAMS;
            _kilos=MIN_KILOS;
        }
        
    }
    /**
     * add grams to current weight object
     * @param grams, the grams to add
     * @return return the weight of the object after addition.
     */
    public Weight add(int grams)
    {
        int common = _kilos*1000 + _grams + grams;
        _grams= common % 1000;
        _kilos= common /1000;
        return this;
    }
    /**
     * return string of weight object
     * @return toString return kilos.grams without zeros in the end.
     */
    public String toString()
    {
        String s="";
        s+=_kilos;
        if (_grams == MIN_GRAMS)//0 done.
            s+="."+ _grams;
        if (_grams > MIN_GRAMS && _grams < 10)//0-9 done.
            s+=".00"+_grams;
        if (_grams >= 10 && _grams < 100)//10-100 done.
        {
            if (_grams % 10 == 0)
                s+=".0"+ _grams / 10;
            else
                s+=".0"+ _grams;
        }
        if (_grams >= 100 && _grams <= MAX_GRAMS)//100-1000 done
        {
            if(_grams % 10 == 0)
                if (_grams % 100 == 0)
                    s+="."+ _grams /100 ;
                else
                    s+="."+ _grams /10 ; 
            else
                s+="."+ _grams  ;
        }
        return s;
    }
    /**
     * return the kilos of the object.
     * @return return the kilos of object.
     */
    public int getKilos()
    {
       return _kilos; 
    }
    /**
     * return the grams of the object.
     * @return return the grams of object.
     */
    public int getGrams()
    {
        return _grams;
    }
    
    /**
     * compere this weight with other weight object.
     * @param other the wieght to be compared to.
     * @return return if weights are equal
     */
    public boolean equals (Weight other)
    {
        return sumWeight(this._kilos,this._grams) == sumWeight(other._kilos,other._grams);
    }
    /**
     * compere if this weight lighter then other weight object.
     * @param other the wieght to be compared to.
     * @return return if this weight is ligter
     */
    public boolean lighter (Weight other)
    {
        return sumWeight(this._kilos,this._grams) < sumWeight(other._kilos,other._grams);
    }
    /**
     * checks if this weight is heavier
     * @param other the wieght to be compared to.
     * @return return if this weight is heavier
     */
    public boolean heavier (Weight other)
    {
        return other.lighter(this);
    }
    
    //suppurt method, summerize kilos and grams for boolean chekings
    private int sumWeight(int kilos, int grams)
    {
            return kilos*1000 + grams;
    }
    //support method, check if valid weight for construcor.
    private boolean isValidWeight(int kilos,int grams)
    {
        return (kilos >= MIN_KILOS && grams >= MIN_GRAMS && grams <= MAX_GRAMS );
    }
   
    

}
