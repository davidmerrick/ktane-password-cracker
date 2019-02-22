let columns = [
    "axxxxx",
    "gxxxxx",
    "axxxxx",
    "axxxxx",
    "ixxxxx",
    "nxxxxx"
]

let words = [
    "about", "after", "again", "below", "could",
    "every", "first", "found", "great", "house",
    "large", "learn", "never", "other", "place",
    "plant", "point", "right", "small", "sound",
    "spell", "still", "study", "their", "there",
    "these", "thing", "think", "three", "water",
    "where", "which", "world", "would", "write"
]

// Naive solution - brute-force
// Todo: Implement a tree search. A ternary search tree looks promising
let filtered = words.filter(word => columns[0].indexOf(word[0]) != -1);
for(var i = 1; i < columns.length; i++){
    filtered = filtered.filter(word => columns[1].indexOf(word[1]) != -1);
}

console.info(filtered);