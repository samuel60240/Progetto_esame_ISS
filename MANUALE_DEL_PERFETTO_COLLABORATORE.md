Il seguente manuale serve a condurre una serena e pacifica collaborazione tra colleghi.
Nel seguente testo troverete istruzioni per caricare e scaricare i lavori da git hub, ordinare i vostri lavori e orientrarvi tra i lavori altrui.

Promemoria, ogni volta che effetuate un nuovo lavoro assicuratevi di controllare lo stato dei commit del progetto,
i quali verranno effettuati in diverse fascie orarie da i vari collaboratori. E' buona abitudine effettuare dei commit-push
almeno ogni mezz'ora/ora di lavoro, per evitare perdita di dati per varia natura.

Quando effettuate la vostra prima esperienza su git hub è normale avere voglia di non farlo, ma vi assicuro che vi sara utile per il vostro futuro, e soprattutto
vi sembrera molto più facile e veloce in seguito.
Prima cosa da fare è copiare il link del progetto, di cui siete stati resi collaboratori, in un area del computer a vostra scelta.(mantenetevi ordinati)
La procedura è la seguente:

    -Bisogna impostare una chiave per i push, questa pocedura è difficile ma si effettua una sola volta.
        windows:
            -Aprire powershell e digitare il comando:
            
                ssh-keygen -t ed25519 -C "tua_email@DiGitHub.com" 
            
            non dimenticare le virgolette,premuto invia e risposto alle domande sempre con invio verranno 
            creati 2 file in un percorso specifico che non ti importa andare a scoprire, i due file sono,
            1 la chiave privata, 
            2 la chiave pubblica.
            Non ti resta che copiare la chiave pubblica con il seguente comando:
                
                Get-Content $env:USERPROFILE\.ssh\id_ed25519.pub | Set-Clipboard
            
            A questo punto avrai la chiave nei tuoi appunti, vai su git hub, Account-> SSh and GPG keys, clicca su new SSH key ed incolla CTRL + V

        linux:
            -Aprire il terminale e digitare il comando:
                
                ssh-keygen -t ed25519 -C "tua_email@example.com"

            -Mostrare la chiave per copiarla col comando:
                
                cat ~/.ssh/id_ed25519.pub

            -Vai su git hub Account->SSH and GPG keys, clicca su new SSH key ed incolla la chiave

        MacOS
            
            ssh-keygen -t ed25519 -C "tua_email@example.com"

            pbcopy < ~/.ssh/id_ed25519.pub




