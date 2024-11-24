[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/XbZw8B6J)
# TODO: Chess Puzzle

TODO: Adott egy sakktábla, melyre egy huszárt és egy királyt helyezünk az 1. ábrán
látható módon. A feladat az, hogy valamelyik figurával a C jelű mezőre
lépjünk. Csak azzal a figurával lehet lépni a sakklépéseknek megfelelően,
amelyik éppen ütésben van a másik által.

## Solution  
  
[(5,1),(5,2),(7,6)]  
L_LEFT_DOWN [(5,1),(6,0),(7,6)]  
L_UP_RIGHT [(5,1),(4,1),(7,6)]  
L_DOWN_RIGHT [(5,1),(6,2),(7,6)]  
L_UP_RIGHT [(5,1),(4,3),(7,6)]  
K_RIGHT [(5,2),(4,3),(7,6)]  
L_DOWN_RIGHT [(5,2),(6,4),(7,6)]  
K_RIGHT [(5,3),(6,4),(7,6)]  
L_RIGHT_DOWN [(5,3),(7,6),(7,6)]  


