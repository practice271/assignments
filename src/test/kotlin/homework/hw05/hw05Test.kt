package homework.hw05

import org.junit.Test

public class Hw05Test{
    @Test fun Test1(){
        var arr:Array<Int>
        var res:Array<Int>
        for (x in 1..4){
            arr = arrayOf(5,0,-1,50,2,3,-25)
            res = arrayOf(-25,-1,0,2,3,5,50)
            sort(x, arr, 0, arr.size()-1)
            org.junit.Assert.assertArrayEquals(res, arr)
        }
    }
    @Test fun Test2(){
        var arr:Array<Int>
        var res:Array<Int>
        for (x in 1..4){
            arr = Array(1000000, {i -> -i})
            res = Array(1000000, {i -> i-999999})
            sort(x, arr, 0, arr.size()-1)
            org.junit.Assert.assertArrayEquals(res, arr)
        }
    }

}
