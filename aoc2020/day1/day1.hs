
day1part1 = do
    contents <- readFile "input.txt"
    let fileLines = lines contents
    let integers = map read fileLines :: [Int]
    return (solve [(x,y) | x<-integers, y<-integers])


solve :: [(Int, Int)] -> Int
solve [] = 0
solve (x:xs)
    | (fst x + snd x) == 2020 = fst x * snd x
    | otherwise = solve xs

day1part2 = do
    contents <- readFile "test.txt"
    let fileLines = lines contents
    let integers = map read fileLines :: [Int]
    return (solve2 [[x,y,z] | x<-integers, y<-integers, z<-integers])


solve2 :: [[Int]] -> Int
solve2 [] = 0
solve2 (x:xs)
    | ((x !! 0) + (x !! 1) + (x !! 2)) == 2020 = (x !! 0) * (x !! 1) * (x !! 2)   
    | otherwise = solve2 xs