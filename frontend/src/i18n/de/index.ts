// This is just an example,
// so you can safely delete all default props below

export default {
  username: 'Nutzername',
  firstName: 'Vorname',
  lastName: 'Nachname',
  email: 'Email Adresse',
  password: 'Passwort',
  passwordRepeat: 'Passwort wiederholen',
  nextStep: 'Weiter',
  createAccount: 'Konto erstellen',
  login: 'Anmelden',
  organization: {
    id: 'ID der Organisation',
    name: 'Name der Organisation',
    description: 'Beschreibung der Organisation',
    noOrganization: 'Es sind keine Organisationen vorhanden. Erstellen Sie eine neue Organisation.',
  },
  cancel: 'Abbrechen',
  recordsPerPage: 'Einträge pro Seite',
  displayedRecords: 'Zeige Einträge {firstItem} - {lastItem} von {totalItem}',
  isActive: 'Aktiv',
  menu: {
    createReport: {
      title: 'Zeitbericht erstellen',
    },
    activity: {
      title: 'Aktivitäten',
    },
    project: {
      title: 'Projekte',
    },
    organization: {
      title: 'Organisationen',
    },
    settings: {
      title: 'Einstellungen',
    },
    users: {
      title: 'Nutzer',
    },
    logout: {
      title: 'Abmelden',
    },
  },
  rules: {
    invalidUsername: 'Der Nutzername darf keine Leerzeichen enthalten.',
    invalidEmail: 'Die angegebene Email Adresse ist nicht korrekt!',
    invalidRepeatPassword: 'Die Passwörter stimmen nicht übereein.',
  },
  title: {
    createAccount: 'Erstelle ein Konto',
    login: 'Anmelden',
    organization: 'Organisationen'
  },
  action: {
    create: {
      organization: 'Organisation anlegen',
    },
    delete: {
      organization: 'Organisation löschen',
    }
  },
  text: {
    delete: {
      organization: '<br><p>Beim Löschen der Organisation werden auch alle Projekte, Aktivitäten und Zeitberichte ' +
        'innerhalb der Organisation gelöscht.</p> Möchten Sie fortfahren?',
    }
  },
  notification: {
    organization: {
      creation: {
        success: 'Die Organisation wurde erfolgreich angelegt.',
        ORGANIZATION_ALREADY_EXISTS: 'Es existiert bereits eine Organisation mit diesem Namen.',
      },
      deletion: {
        success: 'Die Organisation wurde erfolgreich gelöscht.',
      }
    },
    authentication: {
      authenticate: {
        success: 'Sie wurden erfolgreich angemeldet.',
        UNAUTHORIZED: 'Benutzername oder Passwort ist falsch.',
      }
    }
  }

};
