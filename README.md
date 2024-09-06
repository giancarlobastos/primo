# “Primo!”

As a user, I want an engaging and interesting experience to interact with when determining what number I land on when playing the “Primo!” game.

The game is simple, a user presses a spin button and an animation similar to a slot machine/carousel/spinner plays and eventually lands on a number. If the number is Prime, the user wins, if it's not prime they lose.

## FRONT END ONLY

The application must:
Leverage the numbers 1-20
Display the numbers in a random order
Land squarely on the winning number
Animate a scroll-type mechanic which begins quickly and progressively slows down before landing on the number
A distinguishable center or selector indicating to the user what number was landed on
A highlight mechanic to outline to the user where the center of the spinner is currently aligned (eg: numbers getting larger as if passing under a magnifying glass)
Align a sound with the spinner as it spins over each number (think beeps and boops), you can find free sounds at (https://freesound.org/) 
Be able to respin an infinite number of times without having to refresh or re-render

### Technical limitations:

- Must be done in react/next.js
- Any animation, motion, physics engines allowed
- Any html/css allowed
- Must be compilable/demoable in a web-based environment like codepen or some other virtual IDE (for security purposes)

## BACK END ONLY

The application must:
Implement logic to generate a random number between 1 and 20 for each game spin which is cryptographically secure (server seed, client seed, and nonce) for which the results can be decoded using this information after they are generated. Results should be reproducible using (server seed, client seed and nonce).

Include functionality to determine if the generated number is prime and handle game outcomes based on this.
Manage the overall game flow, ensuring correct win/loss determination and responding to the frontend.
Track each user's spin results, including wins and losses, and provide this data when requested.
Ensure the system can handle multiple simultaneous users without issues.
Create endpoints that allow the frontend to start spins, retrieve game results, and access spin history.
Include light documentation regarding the API endpoint for your fellow FE Engineers.

### Technical limitations:

- Must be done in java 11
- Any portable database or in memory storage is allowed. 
- Do not use Spring framework. You can use the JEE or Spark framework.
- Must be compilable/demoable in a web-based environment like codepen or github codespaces.

# How to run the App

```
docker-compose up --build
```

# Implementation notes:

- The backend is keeping history data in a HashMap, it should be store in a database if it was a production app
- The `userId` and `clientSeed` is **hardcoded** into the web page, it could be also passed as query params