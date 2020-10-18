package com.example.list

object Cats {
    var map  = HashMap<Int, Cat>()

    fun getCats(): Collection<Cat>{
        for(i in 0..5){
            var cat1 = Cat("British Cat","Very gorgeous and lordly", R.drawable.british)
            var cat2 = Cat("Barsic","Simple russian cat", R.drawable.russian)
            var cat3 = Cat("Kit","Mamy mav", R.drawable.mav)
            var cat4 = Cat("Tom","Bully", R.drawable.tom)
            var cat5 = Cat("Meme Cat","Lives the way he wants", R.drawable.meme)
            var cat6 = Cat("Simons Cat","Default version", R.drawable.simons)
            map.put(cat1.id, cat1)
            map.put(cat2.id, cat2)
            map.put(cat3.id, cat3)
            map.put(cat4.id, cat4)
            map.put(cat5.id, cat5)
            map.put(cat6.id, cat6)
        }
        return map.values
    }
    fun getCatById(id: Int?): Cat? {
        return map[id]
    }
}