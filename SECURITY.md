# Security Policy

## Versioni supportate

Red Echoes è un progetto universitario in sviluppo. Le correzioni di sicurezza
vengono applicate solo all'ultima versione sul branch `main`.

| Versione | Supportata |
|----------|------------|
| `main`   | ✅         |
| altre    | ❌         |

## Segnalare una vulnerabilità

**Non aprire una issue pubblica** per problemi di sicurezza.

Puoi segnalare un problema in due modi:

1. **Consigliato:** usa il pulsante *Report a vulnerability* nella scheda
   **Security** del repository. La segnalazione resta privata e visibile
   solo ai maintainer.
2. In alternativa, scrivi all'indirizzo email indicato nel profilo GitHub
   del maintainer del progetto.

Includi se possibile:
- descrizione del problema e file coinvolti
- passaggi per riprodurlo
- impatto possibile ed eventuale proposta di correzione

Cercheremo di rispondere entro 7 giorni. Trattandosi di un progetto d'esame,
i tempi possono variare durante le sessioni.

## Ambito

Rientrano nella policy:
- gli script Python del progetto (inclusi quelli eseguiti in Blender)
- i file e gli asset distribuiti nel repository

Non rientrano:
- vulnerabilità di Blender, Python o di altre dipendenze esterne
  (vanno segnalate ai rispettivi progetti)
- problemi che richiedono accesso fisico o amministrativo alla macchina

## Buone pratiche per i collaboratori

- Non committare password, token, chiavi API o dati personali.
- Configurare Git con l'indirizzo *noreply* di GitHub
  (Settings → Emails → *Keep my email addresses private*) per non esporre
  la propria email nei commit.
- Aprire i file `.blend` di terze parti con l'esecuzione automatica degli
  script disattivata (Blender: *Preferences → Save & Load → Auto Run Python Scripts*).
- Usare Pull Request con revisione prima di unire modifiche su `main`.
